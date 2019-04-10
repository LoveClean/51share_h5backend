package com.scriptures.shareApp.controller;

import com.scriptures.shareApp.annotation.ACS;
import com.scriptures.shareApp.controller.request.CommodityUptStatusRequestBean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scriptures.shareApp.controller.request.CommodityAddRequestBean;
import com.scriptures.shareApp.controller.request.CommodityGetFuzzyRequestBean;
import com.scriptures.shareApp.controller.request.CommodityUptReuquestBean;
import com.scriptures.shareApp.controller.response.PageResponseBean;
import com.scriptures.shareApp.dao.entity.Commodity;
import com.scriptures.shareApp.dao.entity.Member;
import com.scriptures.shareApp.service.CommodityService;
import com.scriptures.shareApp.service.MemberService;
import com.scriptures.shareApp.service.impl.CommodityServiceImpl;
import com.scriptures.shareApp.util.ResponseEntity;
import com.scriptures.shareApp.util.ResponseEntityUtil;
import com.scriptures.shareApp.vo.ShareDataWithCommodity;
import com.scriptures.shareApp.vo.newShare;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "商品操作接口", produces = "application/json")
@RestController
@RequestMapping("commodity")
public class CommodityController extends BaseController{
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private MemberService memberService;

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "查看所有可分享商品", notes = "分页查看所有可分享商品")
    @GetMapping("getAllByStatus.do")
    public ResponseEntity<List<Commodity>> getAllByStatus( HttpServletRequest request) {
        Member member=super.getLoginMember(request);
        Member member2;
		if(member==null) {
			member2=null;
		}else {
			member2=memberService.getInfoById(member.getId()).getData();
		}
        return commodityService.getAllByStatus(member2);
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "查看一个商品的详细信息", notes = "查看一个商品的详细信息")
    @GetMapping("getInfo.do")
    public ResponseEntity<Commodity> getInfo(@RequestParam String id) {
        return commodityService.getInfo(id);
    }


    @ApiOperation(value="查看我分享的商品" , notes="根据会员id获取商品分享信息")
	@GetMapping("getCommodityGroupByTypeId")
	public PageResponseBean<ShareDataWithCommodity> getCommodityGroupByTypeId(@RequestParam String memberId,@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
		return commodityService.getCommodityGroupByTypeId(memberId,pageNum,pageSize);
	}
}
