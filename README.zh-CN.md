# Takeaway

**使用 Java、Kotlin、XML 布局和 SQLite 构建的 Android 点餐应用。**

Takeaway 支持浏览地方菜品、在菜单或购物车中调整数量，以及登录后提交订单。每次结算保存为一笔订单，包含菜品明细、数量、总价和备注。配送信息与口味偏好按账号保存在设备上。

| 首页 | 菜单 | 订单历史 |
| --- | --- | --- |
| <img src="docs/images/home.png" width="235" alt="Takeaway 首页和推荐菜品"> | <img src="docs/images/menu.png" width="235" alt="菜单和菜品数量控制"> | <img src="docs/images/order.png" width="235" alt="同一订单中的两种菜品、备注和合计"> |

## 可以做什么

- 从 Home 或 Menu 浏览菜品，两个入口使用相同的菜品数据与价格。
- 添加菜品，在菜单或购物车中调整数量。
- 注册本地账号、登录和退出登录。
- 提交订单，查看菜品数量、小计、总价和备注。
- 为每个账号保存配送信息和口味偏好。

## 点餐流程

菜单中的数量变化会同步到购物车。结算时，应用生成订单编号，在同一个 SQLite 事务中保存全部菜品明细。历史订单按编号分组，显示总价由各条明细的小计累加得到。

没有订单编号的旧记录保留为单独条目。各账号的配送信息与偏好通过 SharedPreferences 保存在本地。

## 本地运行

需要 Android Studio、JDK 8、Android SDK Platform 30 和 Build Tools 30.0.3。项目使用 Gradle Wrapper 6.5。

1. 在 Android Studio 中打开项目根目录，配置对应的 JDK 和 SDK。
2. 在 Windows 下构建 Debug APK。

   ```powershell
   .\gradlew.bat assembleDebug
   ```

   macOS 或 Linux 下运行 `sh ./gradlew assembleDebug`。

3. 在模拟器或 Android 设备中运行 `app` 模块，从 Account 页创建本地测试账号。
4. 在 Menu 选择两种菜品并调整数量，从 Cart 提交订单，再到 **Account → My orders** 查看保存结果。

点餐流程已在 Android 9（API 28）模拟器上验证。

## 项目结构

```text
app/src/main/java/com/example/order/activity/  应用页面与账号入口
app/src/main/java/com/example/order/ui/        首页、菜单、购物车与账号标签页
app/src/main/java/com/example/order/dao/       本地账号与订单存储
app/src/main/java/com/example/order/data/      共用菜品数据
app/src/main/res/                             布局、图标和菜品图片
docs/images/                                  界面截图
docs/project-delivery.md                       Sprint 安排与验收说明
```

## 项目管理与交付

项目从团队课程开发起步。我搭建 Android 应用框架，开发和调试注册登录、菜单、购物车及结算流程，同时用 Jira 拆解任务、跟进进度并协调验收。

交付说明记录了 Sprint 1 的安排与验收条件，并展示了从选菜到查看已保存订单的实测示例。[项目交付说明](docs/project-delivery.md)

## 当前范围

应用提供本地点餐流程，订单保存在设备的 SQLite 数据库中。支付、退款、评价和支付设置入口显示功能暂未开放的提示。餐厅服务对接、支付处理和配送安排仍待实现。
