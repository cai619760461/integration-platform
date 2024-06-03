package com.incaier.integration.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.incaier.integration.platform.entity.Personnel;
import com.incaier.integration.platform.mapper.PersonnelMapper;
import com.incaier.integration.platform.service.PersonnelService;
import com.incaier.integration.platform.util.AesUtil;
import com.incaier.integration.platform.util.SM4Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 人员(Personnel)表服务实现类
 *
 * @author makejava
 * @since 2023-04-26 12:02:22
 */
@Slf4j
@Service("personnelService")
public class PersonnelServiceImpl extends ServiceImpl<PersonnelMapper, Personnel> implements PersonnelService {

    @Autowired
    private PersonnelMapper personnelMapper;

    @Override
    public PageInfo<Personnel> getPersonnelInfo(Integer id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Personnel> list = personnelMapper.selectAll(id);
        return new PageInfo<>(list);
    }

    private final int BATCH_SIZE = 500;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public void resetPasswordToSm4() throws InterruptedException {
        Integer count = personnelMapper.selectCount(null);
        if (count == 0) return;
        int totalPages = (count + BATCH_SIZE - 1) / BATCH_SIZE;
        for (int page = 0; page < totalPages; page++) {
            Thread.sleep(1000);
            int offset = page * BATCH_SIZE;
            taskExecutor.execute(() -> {
                List<Personnel> personnelList = personnelMapper.getPersonnelByPage(offset, BATCH_SIZE);
                log.info("----------密码sm4重置---------，起始索引：{}，结束索引：{}，共{}", personnelList.get(0).getPk(), personnelList.get(personnelList.size() - 1).getPk(), personnelList.size());
                updatePassword(personnelList);
            });
        }
    }

    public void updatePassword(List<Personnel> personnelList) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        List<Personnel> updateList = new ArrayList<>();
        List<Personnel> undefinList = new ArrayList<>();
        try {
            personnelList.forEach(x -> {
                try {
                    //密文
                    String ciphertext = x.getPassWord();
                    // 增加md5校验
                    String password = AesUtil.decrypt(ciphertext);
                    String sm4Password = SM4Util.encryptEcb(SM4Util.DEFAULT_KEY, password, "");
                    x.setSm4PassWord(sm4Password);
                    x.setEncryptMode("SM4");
                    updateList.add(x);
                } catch (Exception e) {
                    x.setEncryptMode("MD5");
                    undefinList.add(x);
                    log.error(JSON.toJSONString(x));
                }
            });
            if (!CollectionUtils.isEmpty(updateList)) {
                personnelMapper.saveOrUpdateBatch(updateList);
            }
            if (!CollectionUtils.isEmpty(undefinList)) {
                personnelMapper.saveOrUpdateBatch(undefinList);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transactionManager.rollback(status);
        }
        transactionManager.commit(status);
    }
}