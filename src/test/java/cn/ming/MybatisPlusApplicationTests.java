package cn.ming;

import cn.ming.mapper.UserMapper;
import cn.ming.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void contextLoads() {
		//查询所有用户
		//这里的参数Wrapper是U一个条件构造器，不用的时候写null
		List<User> users = userMapper.selectList(null);
		for (User user : users) {
			System.out.println(user);
		}
	}

	//测试插入
	@Test
	public void testInsert(){
		User user = new User();
		//自动生成id
		user.setName("单");
		user.setAge(18);
		user.setEmail("741168@qq.com");
		int insert = userMapper.insert(user);
		System.out.println(insert); //受到影响的行数
		System.out.println(user);
	}

	//测试更新
	@Test
	public void testUpdate(){
		User user = new User();
		user.setId(1298122739676188675L);
		user.setName("小明");
		user.setAge(20);
		int i = userMapper.updateById(user);
	}

	//测试乐观锁成功		单线程
	@Test
	public void testOptimisticLocker(){
		//1.查询用户信息
		User user = userMapper.selectById(1L);
		//2.修改用户信息
		user.setName("单子");
		user.setEmail("666666@qq.com");
		//3.执行更新操作
		userMapper.updateById(user);
	}

	//测试乐观锁失败		多线程
	@Test
	public void testOptimisticLocker2(){
		// 线程1
		User user = userMapper.selectById(1L);
		user.setName("单子");
		user.setEmail("666666@qq.com");

		// 线程1
		User user2= userMapper.selectById(1L);
		user2.setName("单单");
		user2.setEmail("999999@qq.com");
		userMapper.updateById(user2);

		//3.执行更新操作		如果没有乐观锁，就会覆盖插队线程的值
		userMapper.updateById(user);
	}

	@Test
	public void testSelectById(){
		User user = userMapper.selectById(1);
		System.out.println(user);
	}

	//测试批量查询
	@Test
	public void testSelectBatchId(){
		List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2,3));
		users.forEach(System.out::println);
	}

	// 条件查询 map
	@Test
	public void testSelectBatchIds(){
		HashMap<String, Object> map = new HashMap<>();
		//自定义要查询的条件
		map.put("name","单单");
		List<User> users = userMapper.selectByMap(map);
		users.forEach(System.out::println);
	}

	//测试分页插件
	@Test
	public void testPage(){
		// 参数1：当前页，参数2：页面大小
		Page<User> page = new Page<>(1,3);
		userMapper.selectPage(page,null);

		page.getRecords().forEach(System.out::println);
	}

	//测试删除
	@Test
	public void testDeleteById(){
		userMapper.deleteById(1298122739676188676L);
	}

	// 通过id批量删除
	@Test
	public void testDeleteBatchId(){
		userMapper.deleteBatchIds(Arrays.asList(1298122739676188677L,1298122739676188679L));
	}

	// 通过map批量删除
	@Test
	public void testDeleteMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("name","jjm");
		userMapper.deleteByMap(map);
	}
}
