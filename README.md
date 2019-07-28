# pizza-backend
## 简述
个人练习项目'pizza'的后端，前端[点此访问](https://github.com/ChaosAlphard/pizza-fontend)

## 构成
- 编写语言
  - Kotlin
- 使用框架
  - Springboot
  - Mybatis
- 数据库
  - MySQL
- 运行环境
  - JDK8

## 接口
- `/pizza` (GET)
  - `/findall`: 查找所有的pizza
  - `/find`: 查找pizza
    - page: 页数
  - `/findbyid`: 按ID查找pizza
    - id: pizza ID
  - `/findbyname`: 按名称查找pizza
    - query: 名称
    - page: 页数
  - `/new` (PUT): 新增pizza
    - `model`: RcPizza (in `com.ilirus.pizzabackend.model.receive`)
  - `/edit` (POST): 更新pizza
    - `model`: UdPizza (in `com.ilirus.pizzabackend.model.receive`)
  - `/delete` (DELETE): 删除pizza
    - id: pizza ID

- `/user` (POST)
  - `/login`: 登录
    - email: 邮箱
    - password: 加密后的密码字符串
  - `/logout`: 注销登录
  - `/register`: 用户注册
    - `model`: RcUser (in `com.ilirus.pizzabackend.model.receive`)
  - `/register/emailexist` (GET): 查找邮箱是否已被注册
  - `/register/phoneexist` (GET): 查找手机号是否已被注册