package sample.spring.yse;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// 책 DAO 클래스
@Repository
public class BookDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	// 책 데이터 입력 쿼리 실행하는 DAO 메서드
	public int insert(Map<String, Object> map) {
		return this.sqlSessionTemplate.insert("book.insert", map);
	}
	
	// 책 데이터를 하나만 가져옴
	public Map<String, Object> selectDetail(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("book.select_detail", map);
	}
	
	// 책 정보 수정
	public int update(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("book.update", map);
	}
	
	// 책 삭제
	public int delete(Map<String, Object> map) {
		return this.sqlSessionTemplate.delete("book.delete", map);
	}
	
	// 책 목록 최신순 조회
	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("book.select_list", map);
	}
}
