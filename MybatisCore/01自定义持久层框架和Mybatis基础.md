# Mybatis

## 第⼀部分：自定义持久层框架

### 1.1  分析JDBC操作问题

```java
public static void main(String[] args) {
         Connection connection = null;
         PreparedStatement preparedStatement = null;
         ResultSet resultSet = null;
     try {
         // 加载数据库驱动
     		Class.forName("com.mysql.jdbc.Driver");
         // 通过驱动管理类获取数据库链接
            connection 		=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?
characterEncoding=utf-8", "root", "root");
         // 定义sql语句？表示占位符
         String sql = "select * from user where username = ?";
         // 获取预处理statement
         preparedStatement = connection.prepareStatement(sql);
         // 设置参数，第⼀个参数为sql语句中参数的序号(从1开始)，第⼆个参数为设置的参数值
        preparedStatement.setString(1, "tom");
         // 向数据库发出sql执⾏查询，查询出结果集
         resultSet = preparedStatement.executeQuery();
         // 遍历查询结果集
         while (resultSet.next()) {
             int id = resultSet.getInt("id");
             String username = resultSet.getString("username");
             // 封装User
             user.setId(id);
             user.setUsername(username);
         }
         System.out.println(user);
         }
 	} catch (Exception e) {
 		e.printStackTrace();
 	} finally {
     // 释放资源
     if (resultSet != null) {
     try {
     resultSet.close();
     } catch (SQLException e) {
     e.printStackTrace();
     }
}
 if (preparedStatement != null) {
 try {
 preparedStatement.close();
 } catch (SQLException e) {
 e.printStackTrace();
 }
}
 if (connection != null) {
 try {
 connection.clospublic static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 通过驱动管理类获取数据库链接
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8",
					"root", "root");
			// 定义sql语句？表示占位符
			String sql = "select * from user where username = ?";
			// 获取预处理statement
			preparedStatement = connection.prepareStatement(sql);
			// 设置参数，第⼀个参数为sql语句中参数的序号(从1开始)，第⼆个参数为设置的参数值
			preparedStatement.setString(1, "tom");
			// 向数据库发出sql执⾏查询，查询出结果集
			resultSet = preparedStatement.executeQuery();
			// 遍历查询结果集
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String username = resultSet.getString("username");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}e();
 } catch (SQLException e) {
 e.printStackTrace();
 }
 }
}
```

#### JDBC问题总结： 

原始jdbc开发存在的问题如下： 

1、 数据库连接创建、释放频繁造成系统资源浪费，从⽽影响系统性能。

2、 Sql语句在代码中硬编码，造成代码不易维护，实际应⽤中sql变化的可能较⼤，sql变动需要改变 java代码。 

3、 使⽤preparedStatement向占有位符号传参数存在硬编码，因为sql语句的where条件不⼀定，可能 多也可能少，修改sql还要修改代码，系统不易维护。 

4、 对结果集解析存在硬编码(查询列名)，sql变化导致解析代码变化，系统不易维护，如果能将数据 库 记录封装成pojo对象解析⽐较⽅便

### 1.2  问题解决思路

①使⽤数据库连接池初始化连接资源

②将sql语句抽取到xml配置⽂件中

③使⽤反射、内省等底层技术，⾃动将实体与表进⾏属性与字段的⾃动映射



### 1.3   自定义框架

#### **使⽤端：**

提供核⼼配置⽂件：

sqlMapConfig.xml : 存放数据源信息，引⼊mapper.xml

Mapper.xml : sql语句的配置⽂件信息

#### **框架端：**

##### 1.  读取配置⽂件

读取完成以后以流的形式存在，我们不能将读取到的配置信息以流的形式存放在内存中，不好操作，可

以创建javaBean来存储

（1）Configuration : 存放数据库基本信息、Map<唯⼀标识，Mapper> 唯⼀标识：namespace + "." + id （2）MappedStatement：sql语句、statement类型、输⼊参数java类型、输出参数java类

##### 2.  解析配置⽂件 

创建sqlSessionFactoryBuilder类： 

⽅法：sqlSessionFactory build()： 

第⼀：使⽤dom4j解析配置⽂件，将解析出来的内容封装到Configuration和MappedStatement中 

第⼆：创建SqlSessionFactory的实现类DefaultSqlSession

##### 3.  创建SqlSessionFactory： 

⽅法：openSession() : 获取sqlSession接⼝的实现类实例对象 4.创建sqlSession接⼝及实现类：主要封装crud⽅法 ⽅法：selectList(String statementId,Object param)：查询所有 selectOne(String statementId,Object param)：查询单个 具体实现：封装JDBC完成对数据库表的查询操作 涉及到的设计模式： Builder构建者设计模式、⼯⼚模式、代理模式

### 1.4  自定义框架实现

在使⽤端项⽬中创建配置配置⽂件 

创建 sqlMapConfig.xml

```xml
<configuration>
 <!--数据库连接信息-->
 <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
 <property name="jdbcUrl" value="jdbc:mysql:///zdy_mybatis"></property>
 <property name="user" value="root"></property>
 <property name="password" value="root"></property>
 <! --引⼊sql配置信息-->
 <mapper resource="mapper.xml"></mapper>
</configuration>
```

mapper.xml

```xml
<mapper namespace="User">
 <select id="selectOne" paramterType="com.lagou.pojo.User"
resultType="com.lagou.pojo.User">
 select * from user where id = #{id} and username =#{username}
 </select>
 
 <select id="selectList" resultType="com.lagou.pojo.User">
 select * from user
 </select>
</mapper>
```

User实体

```java
public class User {
 //主键标识
 private Integer id;
 //⽤户名
 private String username;
 
 public Integer getId() {
 return id;
 }
 public void setId(Integer id) {
 this.id = id;
 }
 public String getUsername() {
 return username;
 }
 public void setUsername(String username) {
 this.username = username;
 }
 @Override
 public String toString() {
 return "User{" +
 "id=" + id +
 ", username='" + username + '\'' + '}';
 }
}
```

再创建⼀个Maven⼦⼯程并且导⼊需要⽤到的依赖坐标

```xml
<properties>
 <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
 <maven.compiler.encoding>UTF-8</maven.compiler.encoding>
 <java.version>1.8</java.version>
 <maven.compiler.source>1.8</maven.compiler.source>
 <maven.compiler.target>1.8</maven.compiler.target>
</properties>
<dependencies>
 <dependency>
 <groupId>mysql</groupId>
 <artifactId>mysql-connector-java</artifactId>
 <version>5.1.17</version>
 </dependency>
 
 <dependency>
 <groupId>c3p0</groupId>
 <artifactId>c3p0</artifactId>
 <version>0.9.1.2</version>
 </dependency>
 
 <dependency>
 <groupId>log4j</groupId>
 <artifactId>log4j</artifactId>
 <version>1.2.12</version>
 </dependency>
 
 <dependency>
 <groupId>junit</groupId>
 <artifactId>junit</artifactId>
 <version>4.10</version>
 </dependency>
 
 <dependency>
 <groupId>dom4j</groupId>
 <artifactId>dom4j</artifactId>
 <version>1.6.1</version>
 </dependency>
 
 <dependency>
 <groupId>jaxen</groupId>
 <artifactId>jaxen</artifactId>
 <version>1.1.6</version>
 </dependency>
</dependencies>
```

Configuration

```java
public class Configuration {
 //数据源
 private DataSource dataSource;
 //map集合： key:statementId value:MappedStatement
 private Map<String,MappedStatement> mappedStatementMap = new HashMap<String,
MappedStatement>();
 public DataSource getDataSource() {
 return dataSource;
}
 public void setDataSource(DataSource dataSource) {
 this.dataSource = dataSource;
}
 public Map<String, MappedStatement> getMappedStatementMap() {
 return mappedStatementMap;
}
 public void setMappedStatementMap(Map<String, MappedStatement>
mappedStatementMap) {
 this.mappedStatementMap = mappedStatementMap;
}
}
```

MappedStatement

```java
public class MappedStatement {
 //id
 private Integer id;
 //sql语句
 private String sql;
 //输⼊参数
 private Class<?> paramterType;
 //输出参数
 private Class<?> resultType;
 public Integer getId() {
 return id;
 }
 public void setId(Integer id) {
 this.id = id;
 }
 public String getSql() {
 return sql;
 }
 public void setSql(String sql) {
 this.sql = sql;
 }
 public Class<?> getParamterType() {
 return paramterType; 
 } 
 public void setParamterType(Class<?> paramterType) {
 this.paramterType = paramterType;
 }
 public Class<?> getResultType() {
 return resultType;
 }
public void setResultType(Class<?> resultType) {
 this.resultType = resultType;
 }
}
```

Resources

```java
public class Resources {
 public static InputStream getResourceAsSteam(String path){ InputStream
resourceAsStream =
 Resources.class.getClassLoader.getResourceAsStream(path);
 return resourceAsStream;
 }
}
```

SqlSessionFactoryBuilder

```java
public class SqlSessionFactoryBuilder {
 private Configuration configuration;
 public SqlSessionFactoryBuilder() {
 this.configuration = new Configuration();
 }
 public SqlSessionFactory build(InputStream inputStream) throws
DocumentException, PropertyVetoException, ClassNotFoundException {
 //1.解析配置⽂件，封装Configuration XMLConfigerBuilder
xmlConfigerBuilder = new
 XMLConfigerBuilder(configuration);
 Configuration configuration =
 xmlConfigerBuilder.parseConfiguration(inputStream);
 //2.创建 sqlSessionFactory
 SqlSessionFactory sqlSessionFactory = new
DefaultSqlSessionFactory(configuration);
 return sqlSessionFactory;
 }
```

XMLConfigerBuilder

```java
public class XMLConfigerBuilder {
private Configuration configuration;
 public XMLConfigerBuilder(Configuration configuration) {
 this.configuration = new Configuration();
 }
 public Configuration parseConfiguration(InputStream inputStream) throws
DocumentException, PropertyVetoException, ClassNotFoundException {
 Document document = new SAXReader().read(inputStream);
//<configuation>
 Element rootElement = document.getRootElement();
 List<Element> propertyElements =
rootElement.selectNodes("//property");
 Properties properties = new Properties();
 for (Element propertyElement : propertyElements) {
 String name = propertyElement.attributeValue("name");
 String value = propertyElement.attributeValue("value");
 properties.setProperty(name,value);
 }
 //连接池
 ComboPooledDataSource comboPooledDataSource = new
 ComboPooledDataSource();
 
comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
 comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
 comboPooledDataSource.setUser(properties.getProperty("username"));
 comboPooledDataSource.setPassword(properties.getProperty("password"));
 //填充 configuration
 configuration.setDataSource(comboPooledDataSource);
 //mapper 部分
 List<Element> mapperElements = rootElement.selectNodes("//mapper");
 XMLMapperBuilder xmlMapperBuilder = new
XMLMapperBuilder(configuration);
 for (Element mapperElement : mapperElements) {
 String mapperPath = mapperElement.attributeValue("resource");
 InputStream resourceAsSteam =
 Resources.getResourceAsSteam(mapperPath);
 xmlMapperBuilder.parse(resourceAsSteam);
 }
 return configuration;
}
```

XMLMapperBuilder

```java
public class XMLMapperBuilder {
private Configuration configuration;
 public XMLMapperBuilder(Configuration configuration) {
 this.configuration = configuration;
 }
 public void parse(InputStream inputStream) throws DocumentException,
ClassNotFoundException {
 Document document = new SAXReader().read(inputStream);
 Element rootElement = document.getRootElement();
 String namespace = rootElement.attributeValue("namespace");
 List<Element> select = rootElement.selectNodes("select");
 for (Element element : select) { //id的值
 String id = element.attributeValue("id");
 String paramterType = element.attributeValue("paramterType");
 String resultType = element.attributeValue("resultType"); //输⼊参
数class
 Class<?> paramterTypeClass = getClassType(paramterType);
 //返回结果class
 Class<?> resultTypeClass = getClassType(resultType);
 //statementId
 String key = namespace + "." + id;
 //sql语句
 String textTrim = element.getTextTrim();
 //封装 mappedStatement
 MappedStatement mappedStatement = new MappedStatement();
 mappedStatement.setId(id);
 mappedStatement.setParamterType(paramterTypeClass);
 mappedStatement.setResultType(resultTypeClass);
 mappedStatement.setSql(textTrim);
 //填充 configuration
 configuration.getMappedStatementMap().put(key, mappedStatement);
 private Class<?> getClassType (String paramterType) throws
ClassNotFoundException {
 Class<?> aClass = Class.forName(paramterType);
 return aClass;
 }
}

```

sqlSessionFactory 接⼝及D efaultSqlSessionFactory 实现类

```java
public interface SqlSessionFactory {
 public SqlSession openSession();
}
public class DefaultSqlSessionFactory implements SqlSessionFactory {
 private Configuration configuration;
 public DefaultSqlSessionFactory(Configuration configuration) {
this.configuration = configuration;
}
 public SqlSession openSession(){
 return new DefaultSqlSession(configuration);
 }
}
```

sqlSession 接⼝及 DefaultSqlSession 实现类

```java
public interface SqlSession {
 public <E> List<E> selectList(String statementId, Object... param)
Exception;
 public <T> T selectOne(String statementId,Object... params) throws
Exception;
 public void close() throws SQLException;
}
```

```java
public class DefaultSqlSession implements SqlSession {
 private Configuration configuration;
 public DefaultSqlSession(Configuration configuration) {
 this.configuration = configuration;
 //处理器对象
 private Executor simpleExcutor = new SimpleExecutor();
 public <E > List < E > selectList(String statementId, Object...param)
throws Exception {
 MappedStatement mappedStatement =
configuration.getMappedStatementMap().get(statementId);
 List<E> query = simpleExcutor.query(configuration,
mappedStatement, param);
 return query;
 }
 //selectOne 中调⽤ selectList
 public <T > T selectOne(String statementId, Object...params) throws
Exception {
 List<Object> objects = selectList(statementId, params);
 if (objects.size() == 1) {
 return (T) objects.get(0);
 } else {
 throw new RuntimeException("返回结果过多");
 }
}
 public void close () throws SQLException {
 simpleExcutor.close();
 }
}
```

Executor

```java
public interface Executor {
 <E> List<E> query(Configuration configuration, MappedStatement
mappedStatement,Object[] param) throws Exception;
 void close() throws SQLException;
}
```

SimpleExecutor

```java
public class SimpleExecutor implements Executor {
 private Connection connection = null;
 public <E> List<E> query(Configuration configuration, MappedStatement
mappedStatement, Object[] param) throws SQLException, NoSuchFieldException,
IllegalAccessException, InstantiationException, IntrospectionException,
InvocationTargetException {
 //获取连接
 connection = configuration.getDataSource().getConnection();
 // select * from user where id = #{id} and username = #{username}
String sql = mappedStatement.getSql();
 //对sql进⾏处理
 BoundSql boundsql = getBoundSql(sql);
 // select * from where id = ? and username = ?
 String finalSql = boundsql.getSqlText();
 //获取传⼊参数类型
 Class<?> paramterType = mappedStatement.getParamterType();
 //获取预编译preparedStatement对象
 PreparedStatement preparedStatement =
connection.prepareStatement(finalSql);
 List<ParameterMapping> parameterMappingList =
boundsql.getParameterMappingList();
 for (int i = 0; i < parameterMappingList.size(); i++) {
 ParameterMapping parameterMapping = parameterMappingList.get(i);
 String name = parameterMapping.getName();
 //反射
 Field declaredField = paramterType.getDeclaredField(name);
 declaredField.setAccessible(true);
 //参数的值
 Object o = declaredField.get(param[0]);
     //给占位符赋值
 preparedStatement.setObject(i + 1, o);
 }
 ResultSet resultSet = preparedStatement.executeQuery();
 Class<?> resultType = mappedStatement.getResultType();
 ArrayList<E> results = new ArrayList<E>();
 while (resultSet.next()) {
 ResultSetMetaData metaData = resultSet.getMetaData();
 (E) resultType.newInstance();
 int columnCount = metaData.getColumnCount();
 for (int i = 1; i <= columnCount; i++) {
 //属性名
 String columnName = metaData.getColumnName(i);
 //属性值
 Object value = resultSet.getObject(columnName);
 //创建属性描述器，为属性⽣成读写⽅法
 PropertyDescriptor propertyDescriptor = new
PropertyDescriptor(columnName, resultType);
 //获取写⽅法
 Method writeMethod = propertyDescriptor.getWriteMethod();
 //向类中写⼊值
 writeMethod.invoke(o, value);
 }
 results.add(o);
 }
 return results;
 }
 @Override
 public void close() throws SQLException {
 connection.close();
 }
 private BoundSql getBoundSql(String sql) {
 //标记处理类：主要是配合通⽤标记解析器GenericTokenParser类完成对配置⽂件等的解
析⼯作，其中TokenHandler主要完成处理
 ParameterMappingTokenHandler parameterMappingTokenHandler = new
ParameterMappingTokenHandler();
 //GenericTokenParser :通⽤的标记解析器，完成了代码⽚段中的占位符的解析，然后再根
据给定的标记处理器(TokenHandler)来进⾏表达式的处理
 //三个参数：分别为openToken (开始标记)、closeToken (结束标记)、handler (标记
处 理器)
 GenericTokenParser genericTokenParser = new GenericTokenParser("# {",
"}", parameterMappingTokenHandler);
 String parse = genericTokenParser.parse(sql);
 List<ParameterMapping> parameterMappings =
parameterMappingTokenHandler.getParameterMappings();
 BoundSql boundSql = new BoundSql(parse, parameterMappings);
 return boundSql;
 }
}
```

BoundSql

```java
public class BoundSql {
 //解析过后的sql语句
 private String sqlText;
 //解析出来的参数
 private List<ParameterMapping> parameterMappingList = new
ArrayList<ParameterMapping>();
 public BoundSql(String sqlText, List<ParameterMapping>
 parameterMappingList) {
 this.sqlText = sqlText;
 this.parameterMappingList = parameterMappingList;
 }
 public String getSqlText() {
 return sqlText;
 }
 public void setSqlText(String sqlText) {
 this.sqlText = sqlText;
 }
 public List<ParameterMapping> getParameterMappingList() {
 return parameterMappingList;
 }
 public void setParameterMappingList(List<ParameterMapping>
parameterMappingList) {
 this.parameterMappingList = parameterMappingList;
 }
}

```

### 1.5  自定义框架优化

通过上述我们的⾃定义框架，我们解决了JDBC操作数据库带来的⼀些问题：例如频繁创建释放数据库连 接，硬编码，⼿动封装返回结果集等问题，但是现在我们继续来分析刚刚完成的⾃定义框架代码，有没 有什么问题？

问题如下：

- dao的实现类中存在重复的代码，整个操作的过程模板重复(创建sqlsession,调⽤sqlsession⽅ 法，关闭 sqlsession）
- dao的实现类中存在硬编码，调⽤sqlsession的⽅法时，参数statement的id硬编码



解决：使⽤代理模式来创建接⼝的代理对象

```java
@Test
 public void test2() throws Exception {
 InputStream resourceAsSteam = Resources.getResourceAsSteam(path：
"sqlMapConfig.xml")
 SqlSessionFactory build = new
SqlSessionFactoryBuilder().build(resourceAsSteam);
 SqlSession sqlSession = build.openSession();
 User user = new User();
 user.setld(l);
 user.setUsername("tom");
 //代理对象
 UserMapper userMapper = sqlSession.getMappper(UserMapper.class);
 User userl = userMapper.selectOne(user);
 System・out.println(userl);
 }
```

在sqlSession中添加⽅法

```java
public interface SqlSession {
 public <T> T getMappper(Class<?> mapperClass);
```

实现类

```java
@Override
 public <T> T getMappper(Class<?> mapperClass) {
 T o = (T) Proxy.newProxyInstance(mapperClass.getClassLoader(), new
Class[] {mapperClass}, new InvocationHandler() {
 @Override
 public Object invoke(Object proxy, Method method, Object[] args)
throws Throwable {
 // selectOne
 String methodName = method.getName();
 // className:namespace
 String className = method.getDeclaringClass().getName();
 //statementid
 String key = className+"."+methodName;
 MappedStatement mappedStatement =
configuration.getMappedStatementMap().get(key);
 Type genericReturnType = method.getGenericReturnType();
 ArrayList arrayList = new ArrayList<> ();
 //判断是否实现泛型类型参数化
 if(genericReturnType instanceof ParameterizedType){
 return selectList(key,args);
 return selectOne(key,args);
 }
 });
return o;
 }
```



## 第二部分: Mybatis相关概念

### 2.1  对象/关系数据库映射（ORM）

ORM全称Object/Relation Mapping：表示对象-关系映射的缩写

ORM完成⾯向对象的编程语⾔到关系数据库的映射。当ORM框架完成映射后，程序员既可以利⽤⾯向 对象程序设

计语⾔的简单易⽤性，⼜可以利⽤关系数据库的技术优势。ORM把关系数据库包装成⾯向对 象的模型。ORM框架

是⾯向对象设计语⾔与关系数据库发展不同步时的中间解决⽅案。采⽤ORM框架 后，应⽤程序不再直接访问底层

数据库，⽽是以⾯向对象的⽅式来操作持久化对象，⽽ORM框架则将这 些⾯向对象的操作转换成底层SQL操作。

ORM框架实现的效果：把对持久化对象的保存、修改、删除 等操作，转换为对数据库的操作

### 2.2  Mybatis简介

MyBatis是⼀款优秀的基于ORM的半⾃动轻量级持久层框架，它⽀持定制化SQL、存储过程以及⾼级映 射。

MyBatis避免了⼏乎所有的JDBC代码和⼿动设置参数以及获取结果集。MyBatis可以使⽤简单的 XML或注解来配置

和映射原⽣类型、接⼝和Java的POJO （Plain Old Java Objects,普通⽼式Java对 象） 为数据库中的记录。

###  2.3  Mybatis历史

原是apache的⼀个开源项⽬iBatis, 2010年6⽉这个项⽬由apache software foundation 迁移到了 google code，

随着开发团队转投Google Code旗下，ibatis3.x正式更名为Mybatis ，代码于2013年11 ⽉迁移到Github。

 iBATIS⼀词来源于“internet”和“abatis”的组合，是⼀个基于Java的持久层框架。iBATIS提供的持久层框 架包括SQL 

Maps和Data Access Objects(DAO)

### 2.4  Mybatis优势

Mybatis是⼀个半⾃动化的持久层框架，对开发⼈员开说，核⼼sql还是需要⾃⼰进⾏优化，sql和java编 码进⾏分

离，功能边界清晰，⼀个专注业务，⼀个专注数据。

分析图示如下：

![image-20221105202240469](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105202240469.png)



## 第三部分: Mybatis基本应用

### 3.1  快速入门

MyBatis官⽹地址：http://www.mybatis.org/mybatis-3/

![image-20221105202332920](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105202332920.png)



#### 3.1.1 开发步骤：

①添加MyBatis的坐标 

②创建user数据表 

③编写User实体类 

④编写映射⽂件UserMapper.xml 

⑤编写核⼼⽂件SqlMapConfig.xml 

⑥编写测试类

#### 3.1.2 环境搭建：

1）导⼊MyBatis的坐标和其他相关坐标

```xml
<properties>
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
<maven.compiler.encoding>UTF-8</maven.compiler.encoding>
<java.version>1.8</java.version>
<maven.compiler.source>1.8</maven.compiler.source>
<maven.compiler.target>1.8</maven.compiler.target>
</properties>
<!--mybatis坐标-->
<dependency>
 <groupId>org.mybatis</groupId>
 <artifactId>mybatis</artifactId>
 <version>3.4.5</version>
</dependency>
<!--mysql驱动坐标-->
<dependency> 
 <groupId>mysql</groupId> 
 <artifactId>mysql-connector-java</artifactId> 
 <version>5.1.6</version> 
 <scope>runtime</scope>
</dependency>
<!--单元测试坐标-->
<dependency> 
 <groupId>junit</groupId> 
 <artifactId>junit</artifactId> 
 <version>4.12</version> 
 <scope>test</scope>
</dependency>
<!--⽇志坐标-->
<dependency> 
 <groupId>log4j</groupId> 
 <artifactId>log4j</artifactId> 
 <version>1.2.12</version>
</dependency>
```

2)创建user数据表

![image-20221105202555541](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105202555541.png)

3)编写User实体

```java
public class User { 
 private int id; 
 private String username; 
 private String password;
 //省略get个set⽅法
}
```

4)编写UserMapper映射⽂件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper 
 PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
 "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="userMapper"> 
 <select id="findAll" resultType="com.lagou.domain.User"> 
 select * from User 
 </select>
</mapper>
```

5)编写MyBatis核⼼⽂件

```xml
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN“
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration> 
 <environments default="development"> 
 <environment id="development"> 
 <transactionManager type="JDBC"/> 
 <dataSource type="POOLED"> 
 <property name="driver" value="com.mysql.jdbc.Driver"/>
 <property name="url" value="jdbc:mysql:///test"/> 
 <property name="username" value="root"/>
 <property name="password" value="root"/> 
 </dataSource> 
 </environment> 
 </environments> 
 <mappers>
 <mapper resource="com/lagou/mapper/UserMapper.xml"/>
 </mappers>
</configuration>

```

6） 编写测试代码

```java
//加载核⼼配置⽂件
InputStream resourceAsStream =
Resources.getResourceAsStream("SqlMapConfig.xml");
//获得sqlSession⼯⼚对象
SqlSessionFactory sqlSessionFactory = new 
 SqlSessionFactoryBuilder().build(resourceAsStream);
//获得sqlSession对象
SqlSession sqlSession = sqlSessionFactory.openSession();
//执⾏sql语句
List<User> userList = sqlSession.selectList("userMapper.findAll");
//打印结果
System.out.println(userList);
//释放资源
sqlSession.close();
```

#### 3.1.3 MyBatis的增删改查操作

 MyBatis的插⼊数据操作 

1)编写UserMapper映射⽂件

```xml
<mapper namespace="userMapper"> 
 <insert id="add" parameterType="com.lagou.domain.User"> 
 insert into user values(#{id},#{username},#{password}) 
 </insert>
</mapper>
```

2)编写插⼊实体User的代码

```java
InputStream resourceAsStream =
Resources.getResourceAsStream("SqlMapConfig.xml");
SqlSessionFactory sqlSessionFactory = new
 SqlSessionFactoryBuilder().build(resourceAsStream);
SqlSession sqlSession = sqlSessionFactory.openSession();
int insert = sqlSession.insert("userMapper.add", user);
System.out.println(insert);
//提交事务
sqlSession.commit();
sqlSession.close();
```

3)插⼊操作注意问题

插⼊语句使⽤insert标签

在映射⽂件中使⽤parameterType属性指定要插⼊的数据类型

Sql语句中使⽤#{实体属性名}⽅式引⽤实体中的属性值

插⼊操作使⽤的API是sqlSession.insert(“命名空间.id”,实体对象); 

插⼊操作涉及数据库数据变化，所以要使⽤sqlSession对象显示的提交事务，即sqlSession.commit()

#### 3.1.4  MyBatis的修改数据操作

1)编写UserMapper映射⽂件

```xml
<mapper namespace="userMapper">
 <update id="update" parameterType="com.lagou.domain.User">
 update user set username=#{username},password=#{password} where id=#
{id}
 </update>
</mapper>
```

2)编写修改实体User的代码

```java
InputStream resourceAsStream =
Resources.getResourceAsStream("SqlMapConfig.xml");
SqlSessionFactory sqlSessionFactory = new
SqlSessionFactoryBuilder().build(resourceAsStream);
SqlSession sqlSession = sqlSessionFactory.openSession();
int update = sqlSession.update("userMapper.update", user);
System.out.println(update);
sqlSession.commit();
sqlSession.close();
```

3)修改操作注意问题

修改语句使⽤update标签

修改操作使⽤的API是sqlSession.update(“命名空间.id”,实体对象);

#### 3.1.5 MyBatis的删除数据操作

1)编写UserMapper映射⽂件

```xml
<mapper namespace="userMapper">
 <delete id="delete" parameterType="java.lang.Integer">
 delete from user where id=#{id}
 </delete>
</mapper>
```

2)编写删除数据的代码

```java
InputStream resourceAsStream =
Resources.getResourceAsStream("SqlMapConfig.xml");
SqlSessionFactory sqlSessionFactory = new
SqlSessionFactoryBuilder().build(resourceAsStream);
SqlSession sqlSession = sqlSessionFactory.openSession();
int delete = sqlSession.delete("userMapper.delete",3);
System.out.println(delete);
sqlSession.commit();
sqlSession.close();
```

3)删除操作注意问题

删除语句使⽤delete标签

Sql语句中使⽤#{任意字符串}⽅式引⽤传递的单个参数

删除操作使⽤的API是sqlSession.delete(“命名空间.id”,Object);

#### 3.1.5 MyBatis的映射⽂件概述

![image-20221105203802555](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105203802555.png)

#### 3.1.6 ⼊⻔核⼼配置⽂件分析：

MyBatis核⼼配置⽂件层级关系

![image-20221105203836388](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105203836388.png)



#### 3.1.7 MyBatis常⽤配置解析

1)environments标签 

数据库环境的配置，⽀持多环境配置

![image-20221105203918345](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105203918345.png)

其中，事务管理器（transactionManager）类型有两种：

- JDBC：这个配置就是直接使⽤了JDBC 的提交和回滚设置，它依赖于从数据源得到的连接来管理事务作 ⽤域。

- MANAGED：这个配置⼏乎没做什么。它从来不提交或回滚⼀个连接，⽽是让容器来管理事务的整个⽣ 命周期（⽐如 JEE 应⽤服务器的上下⽂）。 默认情况下它会关闭连接，然⽽⼀些容器并不希望这样，因 此需要将 

  closeConnection 属性设置为 false 来阻⽌它默认的关闭⾏为。

其中，数据源（dataSource）类型有三种：

- UNPOOLED：这个数据源的实现只是每次被请求时打开和关闭连接。
- POOLED：这种数据源的实现利⽤“池”的概念将 JDBC 连接对象组织起来。

- JNDI：这个数据源的实现是为了能在如 EJB 或应⽤服务器这类容器中使⽤，容器可以集中或在外部配 置数据源，然后放置⼀个 JNDI 上下⽂的引⽤。

2)mapper标签

该标签的作⽤是加载映射的，加载⽅式有如下⼏种：

使⽤相对于类路径的资源引⽤，例如：

```xml
<mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
```

使⽤完全限定资源定位符（URL），例如：

```xml
<mapper url="file:///var/mappers/AuthorMapper.xml"/>
```

使⽤映射器接⼝实现类的完全限定类名，例如：

```xml
<mapper class="org.mybatis.builder.AuthorMapper"/>
```

将包内的映射器接⼝实现全部注册为映射器，例如：

```xml
<package name="org.mybatis.builder"/>
```



#### 3.1.8 Mybatis相应API介绍

SqlSession⼯⼚构建器SqlSessionFactoryBuilder

常⽤API：SqlSessionFactory build(InputStream inputStream)

通过加载mybatis的核⼼⽂件的输⼊流的形式构建⼀个SqlSessionFactory对象

```java
String resource = "org/mybatis/builder/mybatis-config.xml";
InputStream inputStream = Resources.getResourceAsStream(resource);
SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
SqlSessionFactory factory = builder.build(inputStream);
```

其中， Resources ⼯具类，这个类在 org.apache.ibatis.io 包中。Resources 类帮助你从类路径下、⽂ 件系统或⼀个 web URL 中加载资源⽂件。



SqlSession⼯⼚对象SqlSessionFactory

SqlSessionFactory 有多个个⽅法创建SqlSession 实例。常⽤的有如下两个：

![image-20221105204421772](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105204421772.png)



SqlSession会话对象

SqlSession 实例在 MyBatis 中是⾮常强⼤的⼀个类。在这⾥你会看到所有执⾏语句、提交或回滚事务 和获取映射器实例的⽅法。

执⾏语句的⽅法主要有：

```java
<T> T selectOne(String statement, Object parameter)
<E> List<E> selectList(String statement, Object parameter)
int insert(String statement, Object parameter)
int update(String statement, Object parameter)
int delete(String statement, Object parameter)
```

操作事务的⽅法主要有：

```java
void commit() 
void rollback()
```

### 3.2 Mybatis的Dao层实现

#### 3.2.1 传统开发方式

编写UserDao接⼝

```java
public interface UserDao {
 List<User> findAll() throws IOException;
}
```

编写UserDaoImpl实现

```java
public class UserDaoImpl implements UserDao {
 public List<User> findAll() throws IOException {
 InputStream resourceAsStream =
 Resources.getResourceAsStream("SqlMapConfig.xml");
 SqlSessionFactory sqlSessionFactory = new
 SqlSessionFactoryBuilder().build(resourceAsStream);
 SqlSession sqlSession = sqlSessionFactory.openSession();
 List<User> userList = sqlSession.selectList("userMapper.findAll");
 sqlSession.close();
 return userList;
 }
}
```

测试传统⽅式

```java
@Test
public void testTraditionDao() throws IOException {
 UserDao userDao = new UserDaoImpl();
 List<User> all = userDao.findAll();
 System.out.println(all);
}
```

#### 3.2.2 代理开发⽅式

代理开发⽅式介绍

采⽤ Mybatis 的代理开发⽅式实现 DAO 层的开发，这种⽅式是我们后⾯进⼊企业的主流。

Mapper 接⼝开发⽅法只需要程序员编写Mapper 接⼝（相当于Dao 接⼝），由Mybatis 框架根据接⼝ 定义创建

接⼝的动态代理对象，代理对象的⽅法体同上边Dao接⼝实现类⽅法。

Mapper 接⼝开发需要遵循以下规范：

1) Mapper.xml⽂件中的namespace与mapper接⼝的全限定名相同

2) Mapper接⼝⽅法名和Mapper.xml中定义的每个statement的id相同

3) Mapper接⼝⽅法的输⼊参数类型和mapper.xml中定义的每个sql的parameterType的类型相同

4) Mapper接⼝⽅法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同

编写UserMapper接⼝

![image-20221105205104041](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105205104041.png)



测试代理⽅式

```java
@Test
public void testProxyDao() throws IOException {
 InputStream resourceAsStream =
Resources.getResourceAsStream("SqlMapConfig.xml");
 SqlSessionFactory sqlSessionFactory = new
SqlSessionFactoryBuilder().build(resourceAsStream);
 SqlSession sqlSession = sqlSessionFactory.openSession();
 //获得MyBatis框架⽣成的UserMapper接⼝的实现类
 UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
 User user = userMapper.findById(1);
 System.out.println(user);
 sqlSession.close();
}

```



## 第四部分: Mybatis配置文件深入

### 4.1 核⼼配置⽂件SqlMapConfig.xml

#### 4.1.1 MyBatis核⼼配置⽂件层级关系

<img src="https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105205259391.png" alt="image-20221105205259391" style="zoom:50%;" />

### 4.2 MyBatis常⽤配置解析

1)environments标签 

数据库环境的配置，⽀持多环境配置

![image-20221105205413054](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105205413054.png)

其中，事务管理器（transactionManager）类型有两种：

- JDBC：这个配置就是直接使⽤了JDBC 的提交和回滚设置，它依赖于从数据源得到的连接来管理事务作 ⽤域。
- MANAGED：这个配置⼏乎没做什么。它从来不提交或回滚⼀个连接，⽽是让容器来管理事务的整个⽣ 命周期（⽐如 JEE 应⽤服务器的上下⽂）。 默认情况下它会关闭连接，然⽽⼀些容器并不希望这样，因 此需要将 closeConnection 属性设置为 false 来阻⽌它默认的关闭⾏为。

其中，数据源（dataSource）类型有三种：

- UNPOOLED：这个数据源的实现只是每次被请求时打开和关闭连接。
- POOLED：这种数据源的实现利⽤“池”的概念将 JDBC 连接对象组织起来。

- JNDI：这个数据源的实现是为了能在如 EJB 或应⽤服务器这类容器中使⽤，容器可以集中或在外部配 置数据源，然后放置⼀个 JNDI 上下⽂的引⽤。

2)mapper标签

该标签的作⽤是加载映射的，加载⽅式有如下⼏种：

使⽤相对于类路径的资源引⽤，例如：

```xml
<mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
```

使⽤完全限定资源定位符（URL），例如：

```xml
<mapper url="file:///var/mappers/AuthorMapper.xml"/>
```

使⽤映射器接⼝实现类的完全限定类名，例如：

```xml
<mapper class="org.mybatis.builder.AuthorMapper"/>
```

将包内的映射器接⼝实现全部注册为映射器，例如：

```xml
<package name="org.mybatis.builder"/>
```

3)Properties标签 

实际开发中，习惯将数据源的配置信息单独抽取成⼀个properties⽂件，该标签可以加载额外配置的 properties⽂

件

![image-20221105205721862](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105205721862.png)

4)typeAliases标签

类型别名是为Java 类型设置⼀个短的名字。原来的类型名称配置如下

![image-20221105205801386](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105205801386.png)

配置typeAliases，为com.lagou.domain.User定义别名为user

![image-20221105205819348](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105205819348.png)上⾯我们是⾃定义的别名，mybatis框架已经为我们设置好的⼀些常⽤的类型的别名

![image-20221105205839093](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105205839093.png)

### 4.2 映射配置⽂件mapper.xml

动态sql语句 

动态sql语句概述 

Mybatis 的映射⽂件中，前⾯我们的 SQL 都是⽐较简单的，有些时候业务逻辑复杂时，我们的 SQL是 动态变化的，此时在前⾯的学习中我们的 SQL 就不能满⾜要求了。 

参考的官⽅⽂档，描述如下：

![image-20221105205935892](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105205935892.png)

动态 SQL 

我们根据实体类的不同取值，使⽤不同的 SQL语句来进⾏查询。⽐如在 id如果不为空时可以根据id查 询，如果username 不同空时还要加⼊⽤户名作为条件。这种情况在我们的多条件组合查询中经常会碰 到。

```xml
<select id="findByCondition" parameterType="user" resultType="user">
 select * from User
 <where>
 <if test="id!=0">
 and id=#{id}
 </if>
 <if test="username!=null">
 and username=#{username}
 </if>
 </where>
</select>
```

当查询条件id和username都存在时，控制台打印的sql语句如下：

```java
… … …
 //获得MyBatis框架⽣成的UserMapper接⼝的实现类
 UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
 User condition = new User();
 condition.setId(1);
 condition.setUsername("lucy");
 User user = userMapper.findByCondition(condition);
 … … …
```

![image-20221105210100637](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105210100637.png)

当查询条件只有id存在时，控制台打印的sql语句如下：

```java
… … …
//获得MyBatis框架⽣成的UserMapper接⼝的实现类
UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
User condition = new User();
condition.setId(1);
User user = userMapper.findByCondition(condition);
… … …
```

![image-20221105210349086](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105210349086.png)

动态 SQL

循环执⾏sql的拼接操作，例如：SELECT * FROM USER WHERE id IN (1,2,5)。

```xml
<select id="findByIds" parameterType="list" resultType="user">
 select * from User
 <where>
 <foreach collection="list" open="id in(" close=")" item="id"
separator=",">
 #{id}
 </foreach>
 </where>
</select>
```

测试代码⽚段如下：

```java
… … …
//获得MyBatis框架⽣成的UserMapper接⼝的实现类
UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
int[] ids = new int[]{2,5};
List<User> userList = userMapper.findByIds(ids);
System.out.println(userList);
… … …
```

![image-20221105210443114](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105210443114.png)

foreach标签的属性含义如下： 

标签⽤于遍历集合，它的属性：

- collection：代表要遍历的集合元素，注意编写时不要写#{}
- open：代表语句的开始部分
- close：代表结束部分
- item：代表遍历集合的每个元素，⽣成的变量名
- sperator：代表分隔符

SQL⽚段抽取

Sql 中可将重复的 sql 提取出来，使⽤时⽤ include 引⽤即可，最终达到 sql 重⽤的⽬的

```xml
<!--抽取sql⽚段简化编写-->
<sql id="selectUser" select * from User</sql>
<select id="findById" parameterType="int" resultType="user">
 <include refid="selectUser"></include> where id=#{id}
</select>
<select id="findByIds" parameterType="list" resultType="user">
 <include refid="selectUser"></include>
 <where>
 <foreach collection="array" open="id in(" close=")" item="id"
separator=",">
 #{id}
 </foreach>
 </where>
</select>
```



## 第五部分: Mybatis复杂映射开发

### 5.1 ⼀对⼀查询

#### 5.1.1 ⼀对⼀查询的模型

⽤户表和订单表的关系为，⼀个⽤户有多个订单，⼀个订单只从属于⼀个⽤户

⼀对⼀查询的需求：查询⼀个订单，与此同时查询出该订单所属的⽤户

![image-20221105210712725](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105210712725.png)

#### 5.1.2⼀对⼀查询的语句

对应的sql语句：select * from orders o,user u where o.uid=u.id;

查询的结果如下：

![image-20221105211855743](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105211855743.png)

#### 5.1.3 创建Order和User实体

```java
public class Order {
 private int id;
 private Date ordertime;
 private double total;
 //代表当前订单从属于哪⼀个客户
 private User user;
}
public class User {
 
 private int id;
 private String username;
 private String password;
 private Date birthday;
}
```

#### 5.1.4 创建OrderMapper接⼝

```java
public interface OrderMapper {
 List<Order> findAll();
}
```

5.1.5 配置OrderMapper.xml

```xml
<mapper namespace="com.lagou.mapper.OrderMapper">
 <resultMap id="orderMap" type="com.lagou.domain.Order">
 <result column="uid" property="user.id"></result>
 <result column="username" property="user.username"></result>
 <result column="password" property="user.password"></result>
 <result column="birthday" property="user.birthday"></result>
 </resultMap>
 <select id="findAll" resultMap="orderMap">
 select * from orders o,user u where o.uid=u.id
 </select>
</mapper>
```

其中还可以配置如下：

```xml
<resultMap id="orderMap" type="com.lagou.domain.Order">
 <result property="id" column="id"></result>
 <result property="ordertime" column="ordertime"></result>
 <result property="total" column="total"></result>
 <association property="user" javaType="com.lagou.domain.User">
 <result column="uid" property="id"></result>
 <result column="username" property="username"></result>
 <result column="password" property="password"></result>
 <result column="birthday" property="birthday"></result>
 </association>
</resultMap>
```

5.1.6 测试结果

```java
OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
List<Order> all = mapper.findAll();
for(Order order : all){
 System.out.println(order);
}
```

![image-20221105212109670](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105212109670.png)

### 5.2  一对多查询

#### 5.2.1  ⼀对多查询的模型

⽤户表和订单表的关系为，⼀个⽤户有多个订单，⼀个订单只从属于⼀个⽤户 

⼀对多查询的需求：查询⼀个⽤户，与此同时查询出该⽤户具有的订单

![image-20221105212235316](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105212235316.png)

#### 5.2.2 ⼀对多查询的语句

对应的sql语句：

```sql
select *,o.id oid from user u left join orders o on u.id=o.uid;
```

查询的结果如下：

![image-20221105212326149](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105212326149.png)

#### 5.2.3 修改User实体

```java
public class Order {
 private int id;
 private Date ordertime;
 private double total;
 //代表当前订单从属于哪⼀个客户
 private User user;
}
public class User {
 
 private int id;
 private String username;
 private String password;
 private Date birthday;
 //代表当前⽤户具备哪些订单
 private List<Order> orderList;
}
```

#### 5.2.4 创建UserMapper接口

```java
public interface UserMapper {
 List<User> findAll();
}
```

#### 5.2.5 配置UserMapper.xml

```xml
<mapper namespace="com.lagou.mapper.UserMapper">
 <resultMap id="userMap" type="com.lagou.domain.User">
 <result column="id" property="id"></result>
 <result column="username" property="username"></result>
 <result column="password" property="password"></result>
 <result column="birthday" property="birthday"></result>
 <collection property="orderList" ofType="com.lagou.domain.Order">
 <result column="oid" property="id"></result>
<result column="ordertime" property="ordertime"></result>
 <result column="total" property="total"></result>
 </collection>
 </resultMap>
 <select id="findAll" resultMap="userMap">
 select *,o.id oid from user u left join orders o on u.id=o.uid
 </select>
</mapper>
```

#### 5.2.6 测试结果

```java
UserMapper mapper = sqlSession.getMapper(UserMapper.class);
List<User> all = mapper.findAll();
for(User user : all){
 System.out.println(user.getUsername());
 List<Order> orderList = user.getOrderList();
 for(Order order : orderList){
 System.out.println(order);
 }
 System.out.println("----------------------------------");
}
```

![image-20221105212917973](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105212917973.png)

### 5.3 多对多查询

#### 5.3.1 多对多查询的模型

⽤户表和⻆⾊表的关系为，⼀个⽤户有多个⻆⾊，⼀个⻆⾊被多个⽤户使⽤ 

多对多查询的需求：查询⽤户同时查询出该⽤户的所有⻆⾊

![image-20221105213043570](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105213043570.png)

#### 5.3.2 多对多查询的语句

对应的sql语句：

```sql
select u.,r.,r.id rid from user u left join user_role ur on u.id=ur.user_id
 inner join role r on ur.role_id=r.id;
```

查询的结果如下：

![image-20221105213136338](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105213136338.png)

#### 5.3.3 创建Role实体，修改User实体

```java
public class User {
 private int id;
 private String username;
 private String password;
 private Date birthday;
 //代表当前⽤户具备哪些订单
 private List<Order> orderList;
 //代表当前⽤户具备哪些⻆⾊
 private List<Role> roleList;
}
public class Role {
 private int id;
 private String rolename;
}
```

#### 5.3.4 添加UserMapper接⼝⽅法

```java
List<User> findAllUserAndRole();
```

#### 5.3.5 配置UserMapper.xml

```xml
<resultMap id="userRoleMap" type="com.lagou.domain.User">
 <result column="id" property="id"></result>
 <result column="username" property="username"></result>
 <result column="password" property="password"></result>
 <result column="birthday" property="birthday"></result>
 <collection property="roleList" ofType="com.lagou.domain.Role">
<result column="rid" property="id"></result>
 <result column="rolename" property="rolename"></result>
 </collection>
</resultMap>
<select id="findAllUserAndRole" resultMap="userRoleMap">
 select u.*,r.*,r.id rid from user u left join user_role ur on
u.id=ur.user_id
 inner join role r on ur.role_id=r.id
</select>
```

#### 5.3.6 测试结果

```java
UserMapper mapper = sqlSession.getMapper(UserMapper.class);
List<User> all = mapper.findAllUserAndRole();
for(User user : all){
 System.out.println(user.getUsername());
 List<Role> roleList = user.getRoleList();
 for(Role role : roleList){
 System.out.println(role);
 }
 System.out.println("----------------------------------");
}
```

![image-20221105213341281](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20221105213341281.png)



## 第六部分: Mybatis注解开发



## 第七部分: Mybati缓存

## 第八部分: Mybati插件

## 第十部分: Mybatis源码剖析

## 第十部分: Mybatis源码剖析

## 第十-部分:设计模式

## 加餐: Mybatis-Plus