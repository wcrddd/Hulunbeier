package cn.edu.upc.manage.dao;

import cn.edu.upc.manage.model.PlanGuide;

public interface PlanGuideMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table plan_guide
     *
     * @mbg.generated Thu May 14 21:10:50 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table plan_guide
     *
     * @mbg.generated Thu May 14 21:10:50 CST 2020
     */
    int insert(PlanGuide record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table plan_guide
     *
     * @mbg.generated Thu May 14 21:10:50 CST 2020
     */
    int insertSelective(PlanGuide record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table plan_guide
     *
     * @mbg.generated Thu May 14 21:10:50 CST 2020
     */
    PlanGuide selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table plan_guide
     *
     * @mbg.generated Thu May 14 21:10:50 CST 2020
     */
    int updateByPrimaryKeySelective(PlanGuide record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table plan_guide
     *
     * @mbg.generated Thu May 14 21:10:50 CST 2020
     */
    int updateByPrimaryKey(PlanGuide record);
}