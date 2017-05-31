package com.yuplus.publiccloud.enums;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */
public enum ColorEnum {

    RED(0x00),
    PINK(0x01),
    PURPLE(0x02),
    INDIGO(0x03),

    BLUE(0x04),
    CYAN(0x05),
    TEAL(0x06),
    GREEN(0x07),

    ORANGE(0x08),
    YELLOW(0x09),
    BROWN(0x0a),
    GREY(0x0b);

    private int value;

    ColorEnum(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ColorEnum mapValueToTheme(final int value) {
        for (ColorEnum theme : ColorEnum.values()) {
            if (value == theme.getValue()) {
                return theme;
            }
        }
        return getDefault();
    }

    static ColorEnum getDefault()
    {
        return ORANGE;
    }
}
