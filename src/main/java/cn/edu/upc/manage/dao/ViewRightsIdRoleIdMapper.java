package cn.edu.upc.manage.dao;

import cn.edu.upc.manage.model.ViewRightsIdRoleIdWithBLOBs;

import java.util.List;

public interface ViewRightsIdRoleIdMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view_rights_id_role_id
     *
     * @mbg.generated Mon Jul 13 12:00:41 CST 2020
     */
    int insert(ViewRightsIdRoleIdWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table view_rights_id_role_id
     *
     * @mbg.generated Mon Jul 13 12:00:41 CST 2020
     */
    int insertSelective(ViewRightsIdRoleIdWithBLOBs record);

    List<ViewRightsIdRoleIdWithBLOBs> getAll();

    List<ViewRightsIdRoleIdWithBLOBs> getAll2();
}