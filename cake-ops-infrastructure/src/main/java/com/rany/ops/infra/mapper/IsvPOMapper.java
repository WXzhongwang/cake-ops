package com.rany.ops.infra.mapper;

import com.rany.ops.infra.po.IsvPO;

public interface IsvPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table isv
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table isv
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int insert(IsvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table isv
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int insertSelective(IsvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table isv
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    IsvPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table isv
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int updateByPrimaryKeySelective(IsvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table isv
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int updateByPrimaryKey(IsvPO record);
}