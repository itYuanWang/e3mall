package cn.e3.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3.manager.service.ItemService;
import cn.e3.mapper.TbItemMapper;
import cn.e3.pojo.TbItem;
import cn.e3.pojo.TbItemExample;
import cn.e3.utils.DatagridPagebean;
@Service
public class ItemServiceImpl implements ItemService {
	
	//注入商品mapper接口代理对象
	@Autowired
	private TbItemMapper itemMapper;

	/**
	 * 需求:根据id查询商品数据
	 */
	public TbItem findItemByID(Long itemId) {
		// 根据主键查询商品数据
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		return item;
	}

	@Override
	public DatagridPagebean findItemListByPage(Integer page,Integer rows) {
		TbItemExample example=new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		
		DatagridPagebean pageBean=new DatagridPagebean();
		PageInfo<TbItem> pageinfo=new PageInfo<>(list);
		pageBean.setTotal(pageinfo.getTotal());
		pageBean.setRows(list);
		
		return pageBean;
	}

}
