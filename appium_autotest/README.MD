### 查看报告截图
在项目目录下的report_screenshots 中查看测试报告截图
### 查看历史运行生成的HTML报告
在项目目录下的`test-output`文件夹中打开`TestReport_yyyyMMdd_HHmmss.html` 标题格式的文件
### 如何运行测试用例
#### 方法一：使用Maven
1. 确保已安装Java和Maven。
2. 克隆项目到本地：`git clone https://github.com/yourusername/auto_test_quiz.git`
3. 导航到项目目录：`cd auto_test_quiz/appium_autotest`
4. 编译、运行、生成报告：`mvn clean compile test`
5. 查看测试报告：在项目目录下的`test-output`文件夹
6. 中打开`TestReport_yyyyMMdd_HHmmss.html` 标题格式的文件

#### 方法二：在idea 中使用maven 插件运行
1. 确保已安装Java和Maven。
2. 克隆项目到本地：`git clone https://github.com/yourusername/auto_test_quiz.git`
3. 导航到项目目录：`cd auto_test_quiz/appium_autotest`
4. 在idea 中打开auto_test_quiz。
5. 在Maven 菜单中，展开selenium_autotest 模块，点击Liftecycle下的clean、complie，编译项目
6. 在Maven 菜单中，展开selenium_autotest 模块，点击Liftecycle下的test，运行测试用例
7. 查看测试报告：在项目目录下的`test-output`文件夹中打开`TestReport_yyyyMMdd_HHmmss.html` 标题格式的文件

#### 方法三：在idea 中使用Junit 运行
1. 确保已安装Java和Maven。
2. 克隆项目到本地：`git clone https://github.com/yourusername/auto_test_quiz.git`
3. 在idea 中打开auto_test_quiz。
4. 右键点击src/test/java 目录下的com/example/appium/tests/PageNavigationTest.java 文件，选择Run 'PageNavigationTest'

### 手机如何连接Appium Inspector
1. 启动Appium Server：命令行输入appium
2. Appium Inspector 做如下设置：
   - Remote Host：127.0.0.1，Remote Port：4723，Remote Path：/wd/hub
   - Desired Capabilities 设置示例：{
     "appium:platformName": "Android",
     "appium:platformVersion": "14.0.0",
     "appium:deviceName": "AVKYVB2613011058",
     "appium:appPackage": "cn.ianzhang.android",
     "appium:automationName": "UiAutomator2",
     "appium:appActivity": "cn.ianzhang.android.MainActivity"
     }
     - 被测应用名和应用报名如何获取：启动被测应用，adb shell dumpsys window | grep mCurrentFocus
   - 点击 Start Session
     
