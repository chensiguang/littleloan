package com.hexin.pettyLoan.archives.constants;

public class ArchivesConstants {
	// 有效标识
	public static final Short INVALID = 0; // 无效
	public static final Short VALID = 1; // 有效
	
	// 档案状态
	public static final Short ARC_STATUS_SAVE = 1; // 保存
	public static final Short ARC_STATUS_REQUEST = 2; // 申请中
	public static final Short ARC_STATUS_FILING = 3; // 归档
	public static final Short ARC_STATUS_REFUSE = 4; // 拒绝
	
	// 档案来源（终端）
	public static final Short ARC_SRC_PC = 1; // PC
	public static final Short ARC_SRC_APP = 2; // APP
	
	// 档案来源（系统）
	public static final Short ARC_SRC_SYSTEM = 1; // 用户管理
	public static final Short ARC_SRC_CA = 2; // CA系统
	public static final Short ARC_SRC_FINANCE = 3; // 财务管理
	public static final Short ARC_SRC_LOAN = 4; // 信贷系统
	public static final Short ARC_SRC_CRM = 5; // 客户关系
	public static final Short ARC_SRC_LIQUIDATION = 6; // 清算结算
	public static final Short ARC_SRC_ACCOUNTING = 7; // 账务管理
	public static final Short ARC_SRC_CREDIT = 8; // 征信系统
	public static final Short ARC_SRC_TRADE = 9; // 资产交易
	public static final Short ARC_SRC_SUPERVISE = 10; // 监管系统
	public static final Short ARC_SRC_PORTALS = 11; // 内网门户
	public static final Short ARC_SRC_ARCHIVES = 12; // 电子档案
	
	// 档案权限级别
	public static final Short ARC_AUTHORITY_USER = 1; // 用户
	public static final Short ARC_AUTHORITY_ARCCATEGORY = 2; // 档案类别
	public static final Short ARC_AUTHORITY_ARCNO = 3; // 档案
	
	// 用户动作
	public static final Short OPERTYPE_FILI_REQUEST = 1; // 归档申请
	public static final Short OPERTYPE_FILI_APPROVE = 2; // 同意归档
	public static final Short OPERTYPE_FILI_REFUSE = 3; // 拒绝归档
	public static final Short OPERTYPE_MODI_REQUEST = 4; // 变更申请
	public static final Short OPERTYPE_MODI_APPROVE = 5; // 同意变更
	public static final Short OPERTYPE_MODI_REFUSE = 6; // 拒绝变更
	public static final Short OPERTYPE_ELEC_QUERY = 7; // 电子档案查询
	public static final Short OPERTYPE_ELEC_DETAIL = 8; // 电子档案详情表示
	public static final Short OPERTYPE_ELEC_PREVIEW = 9; // 电子档案预览
	public static final Short OPERTYPE_ELEC_DOWNDLOAD = 10; // 电子档案下载
	public static final Short OPERTYPE_DATA_COUNT = 11; // 数据统计
	public static final Short OPERTYPE_ENTI_QUERY = 12; // 实体档案查询
	public static final Short OPERTYPE_ENTI_ADD = 13; // 实体档案录入
	public static final Short OPERTYPE_ENTI_MODIFY = 14; // 实体档案变更
	public static final Short OPERTYPE_ENTI_LEND = 15; // 实体档案借阅
	public static final Short OPERTYPE_ENTI_RENEW = 16; // 实体档案续借
	public static final Short OPERTYPE_ENTI_RETURN = 17; // 实体档案归还
	public static final Short OPERTYPE_ENTI_URGERETURN = 18; // 实体档案催还
	public static final Short OPERTYPE_CATE_QUERY = 19; // 档案类别查询
	public static final Short OPERTYPE_CATE_ADD = 20; // 档案类别创建
	public static final Short OPERTYPE_CATE_MODIFY = 21; // 档案类别修改
	public static final Short OPERTYPE_CATE_REMOVE = 22; // 档案类别删除
	public static final Short OPERTYPE_AUTH_REQ = 23; // 权限申请
	public static final Short OPERTYPE_AUTH_APPROVE = 24; // 同意权限申请
	public static final Short OPERTYPE_AUTH_REFUSE = 25; // 拒绝权限申请
	public static final Short OPERTYPE_AUTH_QUERY = 26; // 权限查询
	public static final Short OPERTYPE_AUTH_GRANT = 27; // 权限设定
	public static final Short OPERTYPE_AUTH_REVOKE = 28; // 权限解除

	// 组织ID
	public static final String ORGNIZATIONID_PLATFORM = "1"; // 平台公司
	
	// 文件添加/删除
	public static final Short FILE_DEL = 1; // 添加
	public static final Short FILE_ADD = 1; // 删除
	

}
