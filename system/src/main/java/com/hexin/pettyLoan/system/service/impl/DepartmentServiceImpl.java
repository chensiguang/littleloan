package com.hexin.pettyLoan.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.hexin.core.annotation.AccessLog;
import com.hexin.core.annotation.AccessLog.Add2DB;
import com.hexin.core.annotation.AccessLog.OperateType;
import com.hexin.core.base.DaoHelper;
import com.hexin.core.exception.ErrorCodeException;
import com.hexin.pettyLoan.common.ErrorCode;
import com.hexin.pettyLoan.system.model.DepartmentItem;
import com.hexin.pettyLoan.system.model.FlexkeyItem;
import com.hexin.pettyLoan.system.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	
	@Resource(name="writeDaoHelper")
	DaoHelper writeDao;
	
	@Resource(name="readDaoHelper")
	DaoHelper readDao;
	/**
	 * param : 机构ID
	 * 取得 部门列表 返回树形结构
	 */
	@Override
	public List<DepartmentItem> getDeptList(DepartmentItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		if(item!=null&&item.getPid()==null){
			item.setPid(0);
		}
		 List<DepartmentItem> list =  readDao.query(DepartmentItem.NAMESPACE, item);
		 list = buildListToTree(list);
		return list;
	}
	/**
	 * param : DepartmentItem
	 * 新增部门
	 */
	@Override
	public DepartmentItem insertDepartment(DepartmentItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try{
			writeDao.insert(DepartmentItem.NAMESPACE, item);//使用唯一键约束
		}
		catch(ErrorCodeException ex){
			ex.printStackTrace();
			throw new ErrorCodeException("SYS00001", ErrorCode.SYS00001, ex);
		}
		return item;
	}


	/**
	 * param : DepartmentItem
	 * 修改部门
	 */
	@Override
	@Transactional(rollbackOn=ErrorCodeException.class)
	public DepartmentItem updateDepartment(DepartmentItem item)
			throws ErrorCodeException {
		// TODO Auto-generated method stub
		try{
			DepartmentItem it = readDao.getOne(DepartmentItem.NAMESPACE,item);
			//取得 之前的部门列表 取得部门路径
			if(it!=null){
				it.setPname(item.getNamePath());
				writeDao.update(DepartmentItem.NAMESPACE,DepartmentItem.SQLID_UPDATE_CHILD,it);
			}
			writeDao.update(DepartmentItem.NAMESPACE, item);
		}
		catch(ErrorCodeException ex){
			throw new ErrorCodeException("SYS00001", ErrorCode.SYS00001, ex);
		}
		return item;
	}


	/**
	 * param : 部门ID
	 * 删除部门 / 为真删除
	 */
	@Override
	public Boolean deleteDepartment(DepartmentItem item) throws ErrorCodeException {
		// TODO Auto-generated method stub
		Integer retCount = (Integer)writeDao.delete(DepartmentItem.NAMESPACE, item);
		return retCount > 0;
	} 

	/**
	 * json树  生成 
	 * @param dirs
	 * @return
	 */
	public  List<DepartmentItem> buildListToTree(List<DepartmentItem> dirs) { 
        List<DepartmentItem> roots = findRoots(dirs); 
        List<DepartmentItem> notRoots = (List<DepartmentItem>) CollectionUtils.subtract(dirs, roots); 
        for (DepartmentItem root : roots) { 
            root.setChildren(findChildren(root, notRoots)); 
        } 
        return roots; 
    } 

    public  List<DepartmentItem> findRoots(List<DepartmentItem> allNodes) { 
        List<DepartmentItem> results = new ArrayList<DepartmentItem>(); 
        for (DepartmentItem node : allNodes) { 
            boolean isRoot = true; 
            for (DepartmentItem comparedOne : allNodes) {
            	if (node.getPid().equals(comparedOne.getId())) { 
                    isRoot = false; 
                    break; 
                } 
            } 
            if (isRoot) { 
                results.add(node); 
            } 
        } 
        return results; 
    } 

    @Override
    public List<DepartmentItem> list(DepartmentItem item) throws ErrorCodeException{
    	return readDao.query(DepartmentItem.NAMESPACE, item);
    }
    
    private  List<DepartmentItem> findChildren(DepartmentItem root, List<DepartmentItem> allNodes) { 
        List<DepartmentItem> children = new ArrayList<DepartmentItem>(); 

        for (DepartmentItem comparedOne : allNodes) { 
            if (comparedOne.getPid().equals(root.getId())) {    
                children.add(comparedOne); 
            } 
        } 
        List<DepartmentItem> notChildren = (List<DepartmentItem>) CollectionUtils.subtract(allNodes, children); 
        for (DepartmentItem child : children) { 
            List<DepartmentItem> tmpChildren = findChildren(child, notChildren); 
            if (tmpChildren == null || tmpChildren.size() < 1) { 
                child.setLeaf(true); 
            } else { 
                child.setLeaf(false); 
               
            } 
            child.setChildren(tmpChildren);
        } 
        return children; 
    }
	


	
}
