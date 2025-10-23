### 查看报告截图
在项目目录下的report_screenshots 中查看测试报告截图
### 查看上一次运行生成的HTML报告
在项目目录下的`target/allure-report`文件夹中打开`index.html`文件
### 如何运行测试用例
#### 方法一：使用Maven
1. 确保已安装Java和Maven。
2. 克隆项目到本地：`git clone https://github.com/yourusername/auto_test_quiz.git`
3. 导航到项目目录：`cd auto_test_quiz/selenium_autotest`
4. 编译、运行、生成报告：`mvn clean compile test allure:report`
5. 查看测试报告：在项目目录下的`target/allure-report`文件夹中打开`index.html`文件。
#### 方法二：在idea 中使用maven 插件运行
1. 确保已安装Java和Maven。
2. 克隆项目到本地：`git clone https://github.com/yourusername/auto_test_quiz.git`
3. 导航到项目目录：`cd auto_test_quiz/selenium_autotest`
4. 在idea 中打开auto_test_quiz。
5. 在Maven 菜单中，展开selenium_autotest 模块，点击Liftecycle下的clean、complie，编译项目
6. 在Maven 菜单中，展开selenium_autotest 模块，点击Liftecycle下的test，运行测试用例
7. 运行后不会自动生成allure报告，需要再执行mvn allure:report 生成报告——右键点击pom.xml，选择Run Maven-New Goal，输入allure:report，点击OK
6. 查看测试报告：在项目目录下的`target/allure-report`文件夹中打开`index.html`文件。
#### 方法三：在idea 中使用Junit 运行
1. 确保已安装Java和Maven。
2. 克隆项目到本地：`git clone https://github.com/yourusername/auto_test_quiz.git`
3. 导航到项目目录：`cd auto_test_quiz/cucumber_autotest`
4. 在idea 中打开auto_test_quiz。
5. 右键点击src/test/java 目录下的com/test/tests/ExceptionsTest.java 文件，选择Run 'ExceptionsTest'
5. 右键点击src/test/java 目录下的com/test/tests/LoginTest.java 文件，选择Run 'LoginTest'
