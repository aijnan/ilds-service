# ilds-service

## 简介

`ilds-service` 是一个基于微服务架构的 WMS（仓库管理系统）示例项目。本项目旨在演示 WMS 应用微服务的拆分。

该项目托管在 GitHub: [https://github.com/aijnan/ilds-service](https://github.com/aijnan/ilds-service)

## 技术栈

*   **主要语言:** Java
*   **构建工具:** Maven

## 项目结构

本项目采用多模块 Maven 项目结构，包含以下主要模块：

*   `ilds-api`: 定义服务间交互的 API 接口或 DTO。
*   `ilds-common`: 存放通用工具类、常量、基础配置等。
*   `ilds-gateway`: API 网关服务，负责请求路由、聚合、认证授权等。
*   `ilds-base-service`: 基础数据服务 (可能包含物料、仓库、库位等)。
*   `ilds-sale-service`: 销售/出库相关服务。
*   `ilds-system-service`: 系统管理服务 (可能包含用户、权限、日志等)。
*   `ilds-vehicle-service`: 车辆/运输相关服务。
