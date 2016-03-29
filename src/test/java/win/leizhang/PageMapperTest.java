/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package win.leizhang;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import win.leizhang.mapper.datasource1.TCountryMapper;
import win.leizhang.model.datasource1.TCountry;

/**
 * Created by liuzh on 2015/3/7.
 */
public class PageMapperTest extends BasicTest {

//    @Autowired
//    private TCountryMapper TCountryMapper;

    @Autowired
    private SqlSession sqlSession;

    @Test
    public void test(){
        TCountryMapper TCountryMapper = sqlSession.getMapper(TCountryMapper.class);
        Example example = new Example(TCountry.class);
        example.createCriteria().andGreaterThan("id",100);
        PageHelper.startPage(2,10);
        List<TCountry> countries = TCountryMapper.selectByExample(example);
        PageInfo<TCountry> pageInfo = new PageInfo<TCountry>(countries);
        System.out.println(pageInfo.getTotal());

        countries = TCountryMapper.selectByExample(example);
        pageInfo = new PageInfo<TCountry>(countries);
        System.out.println(pageInfo.getTotal());
    }
    
// 5. 如何在代码中使用
// http://git.oschina.net/free/Mybatis_PageHelper/blob/master/wikis/HowToUse.markdown
/*    @Test
    public void shouldGetAllCountries() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            List<TCountry> list = sqlSession.selectList("selectAll");
            assertEquals(183, list.size());
        } finally {
            sqlSession.close();
        }
    }
    
    *//**
     * 使用Mapper接口调用时，使用PageHelper.startPage效果更好，不需要添加Mapper接口参数
     *//*
    @Test
    public void testMapperWithStartPage() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        TCountryMapper TCountryMapper = sqlSession.getMapper(TCountryMapper.class);
        try {
            //获取第1页，10条内容，默认查询总数count
            PageHelper.startPage(1, 10, "id desc");
            List<TCountry> list = TCountryMapper.selectAll();
            assertEquals(10, list.size());
            assertEquals(183, ((Page<?>) list).getTotal());


            //获取第2页，10条内容，显式查询总数count
            PageHelper.orderBy("TCountryname desc");
            list = TCountryMapper.selectAll();
            assertEquals(183, list.size());
            assertEquals(183, ((Page<?>) list).getTotal());


            //获取第2页，10条内容，不查询总数count
            PageHelper.startPage(2, 10, false);
            PageHelper.orderBy("id asc");
            list = TCountryMapper.selectAll();
            assertEquals(10, list.size());
            assertEquals(-1, ((Page<?>) list).getTotal());


            //获取第3页，20条内容，默认查询总数count
            PageHelper.orderBy("TCountryname desc");
            PageHelper.startPage(3, 20);
            list = TCountryMapper.selectAll();
            assertEquals(20, list.size());
            assertEquals(183, ((Page<?>) list).getTotal());
        } finally {
            sqlSession.close();
        }
    }
    
    *//**
     * 使用命名空间调用时，使用PageHelper.startPage
     * <p/>
     * startPage第三个参数可以控制是(true)否(false)执行count查询，使用两个查询的startPage时默认进行count查询
     * <p/>
     * 使用startPage方法时，如果同时使用RowBounds，以startPage为准
     *//*
    @Test
    public void testNamespaceWithStartPage() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();

        try {
            //获取第1页，10条内容，默认查询总数count
            PageHelper.startPage(1, 10);
            List<TCountry> list = sqlSession.selectList("selectAll");
            assertEquals(10, list.size());
            assertEquals(183, ((Page<?>) list).getTotal());

            //获取第2页，10条内容，显式查询总数count
            PageHelper.startPage(2, 10, true);
            list = sqlSession.selectList("selectAll");
            assertEquals(10, list.size());
            assertEquals(183, ((Page<?>) list).getTotal());

            //获取第2页，10条内容，不查询总数count
            PageHelper.startPage(2, 10, false);
            list = sqlSession.selectList("selectAll");
            assertEquals(10, list.size());
            assertEquals(-1, ((Page<?>) list).getTotal());

            //获取第3页，20条内容，默认查询总数count
            PageHelper.startPage(3, 20);
            list = sqlSession.selectList("selectAll");
            assertEquals(20, list.size());
            assertEquals(183, ((Page<?>) list).getTotal());
        } finally {
            sqlSession.close();
        }
    }*/
    
    
}
