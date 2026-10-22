package com.ccbupt.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ccbupt.entity.HouseEntity;
import com.ccbupt.entity.R;
import com.ccbupt.service.HouseService;

@RestController
@RequestMapping("/house") // 修复：移除重复的jkshop前缀，因为application.properties已配置server.servlet.context-path=/jkshop
@CrossOrigin("*") // 若前端跨户可放开注释
public class HouseController {
	@Autowired
	HouseService hs;
	
	// ========== 原有房屋接口（保留不动） ==========
	@GetMapping
	public R<List<HouseEntity>> getList(){ // 修复：返回类型改为R<List<HouseEntity>>以匹配前端预期
		List<HouseEntity> list = hs.getAllList();
		return R.ok(list);
	}
	
	@GetMapping("getById/{id}")
    public R<HouseEntity> getById(@PathVariable("id") int id) { // 修复：返回类型改为R<HouseEntity>以匹配前端预期
        HouseEntity entity = hs.getById(id);
        return R.ok(entity);
    }
	
	@GetMapping("{housename}") // 修复：路径参数映射应返回R<List<HouseEntity>>
    public R<List<HouseEntity>> getByKV(@PathVariable("housename") String housename) {
        List<HouseEntity> list = hs.getListByHousename(housename);
        return R.ok(list);
    }
	
	@PostMapping
	public R<HouseEntity> save(@RequestBody HouseEntity he) {
		return hs.save(he);
	}
	
	@PutMapping
	public R<HouseEntity> update(@RequestBody HouseEntity he) {
		return hs.update(he);
	}

	@DeleteMapping("{id}")
	public R<String> delete (@PathVariable("id") int id) { // 修复：返回类型改为R<String>以匹配前端预期
        int result = hs.delete(id);
        if(result > 0) {
            return R.ok("删除成功");
        } else {
            return R.fail("删除失败");
        }
    }
	
	// ========== 新增：房屋图片上传接口 ==========
	/**
	 * 房屋图片上传接口
	 * 接口路径：/jkshop/house/upload（因为类上@RequestMapping是"jkshop/house"）
	 * 请求方式：POST
	 * 请求参数：file（图片文件）
	 * 返回格式：{code:200, data:{imgUrl:"xxx"}, message:"上传成功"}
	 */
	@PostMapping("upload") // 接口完整路径：/jkshop/house/upload
	public R<Map<String, String>> uploadHouseImg(@RequestParam("file") MultipartFile file) {
		// 1. 校验文件是否为空
		if (file.isEmpty()) {
			// 返回失败格式：code非200，message提示错误
			return R.fail("上传失败：文件为空");
		}

		// 2. 定义文件保存路径（SpringBoot静态资源目录，前端可直接访问）
		// 保存到：项目根目录/src/main/resources/static/img/upload/house/
		String projectRoot = System.getProperty("user.dir");
		String uploadRootDir = projectRoot + "/src/main/resources/static/img/upload/house/";
		// 生成唯一文件名（时间戳+原文件名，避免重名）
		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		File destFile = new File(uploadRootDir + fileName);

		try {
			// 3. 自动创建目录（如果父目录不存在）
			if (!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs(); // 递归创建多级目录
			}
			// 4. 将上传的文件保存到服务器指定路径
			file.transferTo(destFile);

			// 5. 构造返回的图片相对路径（前端拼接base.url即可访问）
			String imgUrl = "img/upload/house/" + fileName; // 最终返回的imgUrl格式
			
			// 6. 构造返回数据（匹配你要的JSON格式）
			Map<String, String> dataMap = new HashMap<>();
			dataMap.put("imgUrl", imgUrl); // data里的imgUrl字段
			
			return R.ok(dataMap, "上传成功");

		} catch (IOException e) {
			e.printStackTrace();
			// 异常时返回失败格式
			return R.fail("上传失败：" + e.getMessage());
		}
	}
}


