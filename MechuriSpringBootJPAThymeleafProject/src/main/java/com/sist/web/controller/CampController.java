package com.sist.web.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.ItemDAO;

import com.sist.web.entity.ItemEntity;

@Controller
@RequestMapping("camp/")
public class CampController {
	@Autowired
	private ItemDAO dao;
	
	@GetMapping("camp_item")
	public String camp_item(Model model)
	{
		List<ItemEntity> list=dao.campItemList();
		for(ItemEntity vo:list)
		{
			String image=vo.getImage();
			String [] images=image.split(",");
			vo.setImage(images[0]);
		}
		model.addAttribute("list", list);
		model.addAttribute("main_html", "item/item_list");
		
		return "main";
	}
	
	@GetMapping("item_detail")
	   public String food_detail(int ino,Model model)
	   {
		   ItemEntity vo=dao.findByIno(ino);
		   String image=vo.getImage();
		   String [] images=image.split(",");
		   vo.setImage(images[0]);
		   
		   String description=vo.getDescription();
		   List<String> dList=new ArrayList<String>();
		   StringTokenizer st= new StringTokenizer(description,",");
		   while(st.hasMoreElements())
		   {
			   dList.add(st.nextToken());
		   }
		   model.addAttribute("dList",dList);
		   model.addAttribute("vo", vo);
		   model.addAttribute("main_html", "item/item_detail");
		   return "main";
	   }
	/*
	 * @RequestMapping("camp_find") public String camp_find(String page,Model model)
	 * { if() }
	 */
	@RequestMapping("item_find")
	   public String food_find(String name,String page,Model model)
	   {
		   if(name==null)
			   name="텐트";
		   if(page==null)
			   page="1";
		   int curpage=Integer.parseInt(page);
		   int rowSize=20;
		   int start=(curpage*rowSize)-rowSize;
		   List<ItemEntity> list=dao.itemFindData(name, start);
		   for(ItemEntity vo:list)
			{
				String image=vo.getImage();
				String [] images=image.split(",");
				vo.setImage(images[0]);
			}
		   int totalpage=dao.itemFindTotalPage(name);
		   final int BLOCK=10;
		   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		   if(endPage>totalpage)
			   endPage=totalpage;
		   
		   model.addAttribute("curpage", curpage);
		   model.addAttribute("totalpage", totalpage);
		   model.addAttribute("startPage", startPage);
		   model.addAttribute("endPage", endPage);
		   model.addAttribute("list", list);
		   model.addAttribute("name", name);
		   model.addAttribute("main_html", "item/item_find");
		   return "main";
	   }
	
	@GetMapping("find_detail")
	public String find_detail(int ino,Model model)
	   {
		   ItemEntity vo=dao.findByIno(ino);
		   String image=vo.getImage();
		   String [] images=image.split(",");
		   vo.setImage(images[0]);
		   
		   String description=vo.getDescription();
		   List<String> dList=new ArrayList<String>();
		   StringTokenizer st= new StringTokenizer(description,",");
		   while(st.hasMoreElements())
		   {
			   dList.add(st.nextToken());
		   }
		   model.addAttribute("dList",dList);
		   model.addAttribute("vo", vo);
		   model.addAttribute("main_html", "item/find_detail");
		   return "main";
	   }
}
