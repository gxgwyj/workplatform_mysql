package com.junyang.base.dao;

import java.util.List;

import com.junyang.common.model.page.Page;

/**
 * 基础DAO
 * @author Administrator
 *
 */
public interface BaseDao<T> {
	  /**
	   * 插入实体 
	   * @param t
	   */
      void insertModel(T t);
      /**
       * 删除实体
       * @param t
       */
      void deleteModel(T t);
      /**
       * 更新实体
       * @param t
       */
      void updateModel(T t);
      /**
       * 查询实体
       * @param
       */
      List<T> selectModels();
      /**
       * 根据id或得特定的实体
       * @param id
       * @return
       */
      T selectModel(String id);
      /**
       * 批量添加实体
       * @param models
       */
      void insertModels(List<T> models);
}
