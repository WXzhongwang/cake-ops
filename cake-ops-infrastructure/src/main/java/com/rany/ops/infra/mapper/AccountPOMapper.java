package com.rany.ops.infra.mapper;

import com.rany.ops.infra.po.AccountPO;

public interface AccountPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int insert(AccountPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int insertSelective(AccountPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    AccountPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int updateByPrimaryKeySelective(AccountPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated Sat Jun 08 15:31:03 CST 2024
     */
    int updateByPrimaryKey(AccountPO record);
}