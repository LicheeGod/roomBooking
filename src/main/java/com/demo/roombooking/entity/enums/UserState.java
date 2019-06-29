package com.demo.roombooking.entity.enums;

public enum UserState {
    HAS_DELETE, //被假删除[屏蔽-0]
    PASS_VERIFIED, //实名-1
    NOTPASS_VERIFIED,  //未实名-2
    BLACKLIST // 黑名单-3
}
