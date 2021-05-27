package sample.spring.yse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// 책 컨트롤러 클래스
@Controller
public class BookController {
	// 책 입력 기능 서비스를 호출하기 위해 서비스 빈을 추가
	@Autowired
	BookService bookService;
		
	// 브라우저에서 /create 주소가 GET 방식으로 입력되었을때 book/create 경로의 뷰를 보여줌
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		return new ModelAndView("book/create");
	}
	
	// 서비스를 이용해 책을 입력하는 컨트롤러 메소드
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		String book_id = this.bookService.create(map);
		
		if(book_id == null) {
			mav.setViewName("redirect:/create");
		}
		else {
			mav.setViewName("redirect:/detail?bookId=" + book_id);
		}
		
		return mav;
	}
	
	// 책 상세 URL이 입력되면 실행되는 메서드
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookService.detail(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detailMap);
		
		String bookId = map.get("bookId").toString();
		mav.addObject("bookId", bookId);
		mav.setViewName("/book/detail");
		
		return mav;
	}
	
	// 책 수정 화면 컨트롤러 메서드 추가
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.bookService.detail(map);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("data", detailMap);
		mav.setViewName("/book/update");
		return mav;
	}
	
	// 책 수정 화면 컨트롤러 메서드
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView updatePost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		boolean isUpdateSuccess = this.bookService.edit(map);
		
		if(isUpdateSuccess) {
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		}
		else {
			mav =  this.update(map);
		}
		
		return mav;
	}
	
	// 책 삭제 기능 컨트롤러 메서드
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		boolean isDeleteSuccess = this.bookService.remove(map);
		
		if(isDeleteSuccess) {
			mav.setViewName("redirect:/list");
		}
		else {
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		}
		
		return mav;
	}
	
	// 책 최신순 리스트 조회 컨트롤러 메서드
	@RequestMapping(value = "list")
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> list = this.bookService.list(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", list);
		
		if (map.containsKey("keyword")) {  
			mav.addObject("keyword", map.get("keyword"));  
		}  
		
		mav.setViewName("/book/list");
		
		return mav;
	}
}



















