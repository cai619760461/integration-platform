package com.incaier.integration.platform.enums;

import lombok.Getter;

/**
 * 医生评分事件类型
 *
 * @author caiweijie
 * @date 2024/06/19
 */
@Getter
public enum DoctorScoreEventType {

    /**
     * 减分
     */
    DECREMENT(0) {
        @Override
        public int apply(int currentScore, int score) {
            return Math.max(currentScore - score, 0);
        }
    },
    /**
     * 加分
     */
    INCREMENT(1) {
        @Override
        public int apply(int currentScore, int score) {
            return currentScore + score;
        }
    };

    private final int value;

    DoctorScoreEventType(int value) {
        this.value = value;
    }

    public static DoctorScoreEventType fromValue(int value) {
        for (DoctorScoreEventType type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid event type value: " + value);
    }

    public abstract int apply(int currentScore, int score);
}
