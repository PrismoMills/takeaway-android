<div align="center">

<h1>Takeaway</h1>
<p><strong>浏览菜品、调整购物车、保存每一次点餐。</strong></p>
<p>支持本地账号、个人偏好与订单历史的 Android 点餐应用。</p>

<p>
<img alt="Android 应用" src="https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white">
<img alt="Java 与 Kotlin" src="https://img.shields.io/badge/Code-Java_%2B_Kotlin-7F52FF">
<img alt="SQLite 存储" src="https://img.shields.io/badge/Storage-SQLite-4479A1?logo=sqlite&logoColor=white">
</p>

<p><a href="#界面一览">界面</a> · <a href="#从选菜到订单历史">点餐流程</a> · <a href="#在-android-上运行">构建与运行</a> · <a href="README.md">English</a></p>

</div>

## 界面一览

| 找到想吃的菜 | 调整菜品数量 | 回看一笔订单 |
| --- | --- | --- |
| <img src="docs/images/home.png" width="235" alt="首页与推荐菜品"> | <img src="docs/images/menu.png" width="235" alt="菜单与数量控制"> | <img src="docs/images/order.png" width="235" alt="订单数量、备注与总价"> |

Takeaway 把浏览、加购和查看历史订单连在一起。用户可以从首页或菜单选择地方菜品，在菜单或购物车里调整数量，登录后保存订单。

## 从选菜到订单历史

| 🍜 浏览菜品 | 🛒 准备下单 | 📋 保存明细 |
| --- | --- | --- |
| 首页与菜单共用菜品数据和价格。 | 数量调整同步到购物车。 | 每次结算有独立编号，包含数量、小计、总价与备注。 |

**账号记住你的偏好。** 注册、登录后，可以按账号保存配送信息和口味设置，也可以从 Account 页退出登录。

**一笔订单完整保存。** 全部菜品明细在同一个 SQLite 事务中写入，历史订单按编号分组、累加各条小计。没有订单编号的旧记录保留为单独条目。

## 在 Android 上运行

在 Android Studio 中打开仓库根目录，配置下列工具。

| 工具 | 版本 |
| --- | --- |
| JDK | 8 |
| Android SDK Platform | 30 |
| Android Build Tools | 30.0.3 |
| Gradle Wrapper | 6.5，仓库已包含 |

Windows 下构建 Debug APK。

```powershell
.\gradlew.bat assembleDebug
```

macOS 或 Linux 下运行 `sh ./gradlew assembleDebug`。在模拟器或 Android 设备中运行 `app` 模块。

**体验一次完整点餐**

1. 从 **Account** 创建本地测试账号。
2. 在 **Menu** 选择两种菜品并调整数量。
3. 从 **Cart** 提交订单。
4. 到 **Account → My orders** 查看保存的明细和总价。

这条流程已在 Android 9（API 28）模拟器上验证。

<details>
<summary><strong>技术实现与代码位置</strong></summary>

应用使用 Java、少量 Kotlin、XML 布局和 SQLite。配送信息与口味偏好通过按账号区分的 SharedPreferences 保存。

主要源码位于 `app/src/main/java/com/example/order/`。其中 `activity/` 放置页面入口，`ui/` 对应各标签页，`dao/` 负责账号和订单存储，`data/` 提供共用菜品数据。布局、图标和菜品图片在 `app/src/main/res/`，界面截图在 `docs/images/`。

</details>

## 项目背景

项目从团队课程开发起步。我搭建 Android 应用框架，开发和调试注册登录、菜单、购物车及结算流程，同时用 Jira 拆解任务、跟进进度并协调验收。

交付说明记录了 Sprint 1 的安排、验收条件和点餐实测示例。[项目交付说明](docs/project-delivery.md)

## 当前体验范围

点餐流程在本地运行，订单保存在设备的 SQLite 数据库中。支付、退款、评价和支付设置入口显示功能暂未开放的提示。餐厅服务对接、支付处理和配送安排仍待实现。
