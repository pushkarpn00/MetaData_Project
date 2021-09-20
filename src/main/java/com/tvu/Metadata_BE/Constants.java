/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE;
public final class Constants {
  public static final class API {
    public static final String NAME_API = "MetaData";
    public static final String SUCCESS_CODE = "0x0";
    public static final String ERROR_CODE ="0x5";
    public static final String SUCCESS_MESSAGE = "OK";
    public static final String FAIL_MESSAGE = "Failed";
    public static final String INVALID_PARAMETER = "Invaild Parameters Values";
    public static final String SUCCESS_MESSAGE_GET = "OK"; 
    public static final String SUCCESS_DOWNLOAD_MESSAGE = "Downloaded";
    public static final String ERROR_DOWNLOAD_MESSAGE = "Unable to download";
    public static final String PROGRESS_OPERATION_CODE = "9001";
    public static final String PASS_FAIL_OPERATION_CODE = "9002";
    public static final String TaskNOtFound = "Task id not found";
    public static final int Failed=0;
    public static final int Merge=1;
    public static final int Ready=2;
    public static final int Delete=3;
    public static final int Normal=4;
    public static final int taskidnotfound=5;
    public static final String logger="debug.log";
  }

}
