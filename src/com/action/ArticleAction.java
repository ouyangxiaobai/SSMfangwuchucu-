package com.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Article;
import com.service.ArticleService;
import com.util.VeDate;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/article")
public class ArticleAction extends BaseAction {
	// 注入Service 由于标签的存在 所以不需要getter setter
	@Autowired
	@Resource
	private ArticleService articleService;

	// 准备添加数据
	@RequestMapping("createArticle.action")
	public String createArticle() {
		return "admin/addarticle";
	}

	// 添加数据
	@RequestMapping("addArticle.action")
	public String addArticle(Article article) {
		article.setAddtime(VeDate.getStringDateShort());
		article.setHits("0");
		this.articleService.insertArticle(article);
		return "redirect:/article/createArticle.action";
	}

	// 通过主键删除数据
	@RequestMapping("deleteArticle.action")
	public String deleteArticle(String id) {
		this.articleService.deleteArticle(id);
		return "redirect:/article/getAllArticle.action";
	}

	// 批量删除数据
	@RequestMapping("deleteArticleByIds.action")
	public String deleteArticleByIds() {
		String[] ids = this.getRequest().getParameterValues("articleid");
		for (String articleid : ids) {
			this.articleService.deleteArticle(articleid);
		}
		return "redirect:/article/getAllArticle.action";
	}

	// 更新数据
	@RequestMapping("updateArticle.action")
	public String updateArticle(Article article) {
		this.articleService.updateArticle(article);
		return "redirect:/article/getAllArticle.action";
	}

	// 显示全部数据
	@RequestMapping("getAllArticle.action")
	public String getAllArticle(String number) {
		List<Article> articleList = new ArrayList<Article>();
		List<Article> tempList = new ArrayList<Article>();
		tempList = this.articleService.getAllArticle();
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 10 == 0) {
			maxPage = maxPage / 10;
		} else {
			maxPage = maxPage / 10 + 1;
		}
		if (number == null) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 10;
		int over = (Integer.parseInt(number) + 1) * 10;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			Article article = tempList.get(i);
			articleList.add(article);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"article/getAllArticle.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"article/getAllArticle.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"article/getAllArticle.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"article/getAllArticle.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		this.getRequest().setAttribute("html", html);
		this.getRequest().setAttribute("articleList", articleList);
		return "admin/listarticle";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryArticleByCond.action")
	public String queryArticleByCond(String cond, String name) {
		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		if (cond != null) {
			if ("title".equals(cond)) {
				article.setTitle(name);
				articleList = this.articleService.getArticleByLike(article);
			}
			if ("image".equals(cond)) {
				article.setImage(name);
				articleList = this.articleService.getArticleByLike(article);
			}
			if ("contents".equals(cond)) {
				article.setContents(name);
				articleList = this.articleService.getArticleByLike(article);
			}
			if ("addtime".equals(cond)) {
				article.setAddtime(name);
				articleList = this.articleService.getArticleByLike(article);
			}
			if ("hits".equals(cond)) {
				article.setHits(name);
				articleList = this.articleService.getArticleByLike(article);
			}
		}
		this.getRequest().setAttribute("articleList", articleList);
		return "admin/queryarticle";
	}

	// 按主键查询数据
	@RequestMapping("getArticleById.action")
	public String getArticleById(String id) {
		Article article = this.articleService.getArticleById(id);
		this.getRequest().setAttribute("article", article);
		return "admin/editarticle";
	}

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

}
