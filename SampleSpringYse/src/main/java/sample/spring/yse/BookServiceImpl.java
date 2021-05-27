package sample.spring.yse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * 비즈니스 클래스가 위치하는 곳 
 * 스프링 MVC 구조에서 서비스 클래스는 컨트롤러와 DAO를 연결하는 역할
 * 
 * 서비스는 DAO를 호출한 결과를 바로 리턴하는 일만 함
 */
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookDAO bookDAO;
	
	// BookDAO.insert 메서드를 실행시키는 서비스 메서드
	@Override
	public String create(Map<String, Object> map) {
		int affectRowCount = this.bookDAO.insert(map);
		
		if(affectRowCount == 1) {
			return map.get("book_id").toString();
		}
		
		return null;
	}
	
	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		return this.bookDAO.selectDetail(map);
	}
	
	@Override
	public boolean edit(Map<String, Object> map) {
		int affectRowCount = this.bookDAO.update(map);
		
		// 수정의 경우 입력과는 다르게 PK를 가져오거나 하는 절차가 필요없으므로 그저 1개의 행이 제대로 영향받았는지만 검사하면 됨
		return affectRowCount == 1;
	}
	
	@Override
	public boolean remove(Map<String, Object> map) {
		int affectRowCount = this.bookDAO.delete(map);
		
		// 수정과 동일하게 한 개의 행이 제대로 영향받았는지만 검사하면 됨
		return affectRowCount == 1;
	}

	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		return this.bookDAO.selectList(map);
	}
}
