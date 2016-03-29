package win.leizhang;

import java.util.Arrays;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import win.leizhang.base.SqlHelper;
import win.leizhang.mapper.datasource1.TCountryMapper;

public class SqlHelperTest extends BasicTest {

	@Autowired
	private SqlSession sqlSession;

	//@Test
	public void test() {
		// SqlSession sqlSession = DynamicHelper.getSqlSession();
		try {
			TCountryMapper countryMapper = sqlSession.getMapper(TCountryMapper.class);

			System.out.println(SqlHelper.getNamespaceSql(sqlSession,
					"com.github.pagehelper.mapper.CountryMapper.selectIf2ListAndOrder"));

			System.out.println(SqlHelper.getMapperSql(countryMapper, "selectIf2ListAndOrder", Arrays.asList(1, 2)));

			System.out.println(
					SqlHelper.getMapperSql(sqlSession, "com.github.pagehelper.mapper.CountryMapper.selectAll"));

			System.out.println(SqlHelper.getMapperSql(sqlSession,
					"com.github.pagehelper.mapper.CountryMapper.selectIf2ListAndOrder", Arrays.asList(1, 2),
					Arrays.asList(3, 4), "id"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
}
