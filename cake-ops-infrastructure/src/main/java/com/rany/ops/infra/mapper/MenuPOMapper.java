package com.rany.ops.infra.mapper;

import com.rany.ops.infra.po.MenuPO;

public interface MenuPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    int insert(MenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    int insertSelective(MenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    MenuPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    int updateByPrimaryKeySelective(MenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Thu Feb 06 19:21:19 CST 2025
     */
    int updateByPrimaryKey(MenuPO record);
}