package com.hongyi.hr.module.hy;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestYuDaoRename {
    public static void main(String[] args) {
//        replaceSidebar();
//        replaceName();
//        updateSidebar();
//        addQuotesToHref();
//        update1line();
//        replacenewcontent();
//        replace17aside();
        replacetargetnull();
    }

      private static void replacetargetnull() {
        String path = "D:\\table\\芋道\\单体文档--yudao-vue-pro\\yudao-vue-pro1";
        try {
            Path basePath = Paths.get(path);
            int replaceCount = 0;

            Files.walk(basePath)
                    .filter(Files::isRegularFile)
                    .filter(filePath -> filePath.toString().endsWith(".html"))
                    .forEach(filePath -> {
                        try {
                            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
                            if (lines.size() >= 17) {
                                String line17 = lines.get(16);
                                Pattern asidePattern = Pattern.compile("<aside[^>]*>(.*?)</aside>", Pattern.DOTALL);
                                Matcher matcher = asidePattern.matcher(line17);

                                if (matcher.find()) {
                                    String asideContent = matcher.group(1);
                                    Pattern hrefPattern = Pattern.compile("href\\s*=\\s*\"([^\"]*)\"");
                                    Matcher hrefMatcher = hrefPattern.matcher(asideContent);
                                    StringBuffer sb = new StringBuffer();
                                    int hrefCount = 0;

                                    while (hrefMatcher.find()) {
                                        String hrefValue = hrefMatcher.group(1);
                                        String newHrefValue = hrefValue.replaceAll("\\s+", "");

                                        if (!hrefValue.equals(newHrefValue)) {
                                            String newHref = "href=\"" + newHrefValue + "\"";
                                            hrefMatcher.appendReplacement(sb, newHref);
                                            hrefCount++;
                                        } else {
                                            hrefMatcher.appendReplacement(sb, hrefMatcher.group(0));
                                        }
                                    }
                                    hrefMatcher.appendTail(sb);

                                    if (hrefCount > 0) {
                                        String newAsideContent = sb.toString();
                                        String newLine17 = line17.replace(asideContent, newAsideContent);
                                        lines.set(16, newLine17);
                                        Files.write(filePath, lines, StandardCharsets.UTF_8);
//                                        replaceCount++;
                                        System.out.println("已处理文件：" + filePath.getFileName() + " (修复 " + hrefCount + " 处 href)");
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            System.out.println("\n处理完成！共替换 " + replaceCount + " 个文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void replace17aside() {
        String path = "D:\\table\\芋道\\单体文档--yudao-vue-pro\\yudao-vue-pro1";
        try {
            Path basePath = Paths.get(path);
            int replaceCount = 0;

            Files.walk(basePath)
                    .filter(Files::isRegularFile)
                    .filter(filePath -> filePath.toString().endsWith(".html"))
                    .forEach(filePath -> {
                        try {
                            String content = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
                            String updatedContent = content.replace("OAuth2.0（SSO 单点登录).html", "OAuth2.0(SSO单点登录).html");
//                            updatedContent=updatedContent.replace("SaaS 多租户【数据库隔离】.html", "SaaS多租户【数据库隔离】.html");
                            updatedContent=updatedContent.replace("MyBatis 联表&分页查询.html", "MyBatis联表&分页查询.html");

                            if (!content.equals(updatedContent)) {
                                FileUtils.write(filePath.toFile(), updatedContent, StandardCharsets.UTF_8);
                                System.out.println("已替换文件：" + filePath.getFileName());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            System.out.println("\n处理完成！共替换 " + replaceCount + " 个文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static void replace17() {
        String targetpath = " <nav class=\"nav-links sf-hidden\"></nav> <ul class=sidebar-links> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>萌新必读</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../萌新必读/简介.html\" class=sidebar-link>简介</a> <li><a href=\"https://doc.iocoder.cn/qun/\" class=sidebar-link>交流群</a> <li><a href=\"https://doc.iocoder.cn/video/\" class=sidebar-link>视频教程</a> <li><a href=\"https://doc.iocoder.cn/feature/\" class=sidebar-link>功能列表</a> <li><a href=\"https://doc.iocoder.cn/quick-start/\" class=sidebar-link>快速启动（后端项目）</a> <li><a href=\"https://doc.iocoder.cn/quick-start-front/\" class=sidebar-link>快速启动（前端项目）</a> <li><a href=\"../萌新必读/接口文档.html\" class=sidebar-link>接口文档</a> <li><a href=\"../萌新必读/技术选型.html\" class=sidebar-link>技术选型</a> <li><a href=\"../萌新必读/项目结构.html\" class=sidebar-link>项目结构</a> <li><a href=\"../萌新必读/代码热加载.html\" class=sidebar-link>代码热加载</a> <li><a href=\"../萌新必读/一键改包.html\" class=sidebar-link>一键改包</a> <li><a href=\"../萌新必读/删除功能.html\" class=sidebar-link>删除功能</a> <li><a href=\"../萌新必读/内网穿透.html\" class=sidebar-link>内网穿透</a> <li><a href=\"../萌新必读/达梦数据库专属.html\" class=sidebar-link>达梦数据库专属</a></ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>后端手册</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../后端手册/新建模块.html\" class=sidebar-link>新建模块</a> <li><a href=\"../后端手册/代码生成【单表】（新增功能）.html\" class=sidebar-link>代码生成【单表】（新增功能）</a> <li><a href=\"../后端手册/代码生成【主子表】.html\" class=sidebar-link>代码生成【主子表】</a> <li><a href=\"../后端手册/代码生成（树表）.html\" class=sidebar-link>代码生成（树表）</a> <li><a href=\"../后端手册/功能权限.html\" class=sidebar-link>功能权限</a> <li><a href=\"../后端手册/数据权限.html\" class=sidebar-link>数据权限</a> <li><a href=\"../后端手册/用户体系.html\" class=sidebar-link>用户体系</a> <li><a href=\"../后端手册/三方登录.html\" class=sidebar-link>三方登录</a> <li><a href=\"../后端手册/OAuth2.0（SSO 单点登录).html\" class=sidebar-link>OAuth 2.0（SSO 单点登录)</a> <li><a href=\"../后端手册/SaaS 多租户【字段隔离】.html\" class=sidebar-link>SaaS 多租户【字段隔离】</a> <li><a href=\"../后端手册/SaaS 多租户【数据库隔离】.html\" class=sidebar-link>SaaS 多租户【数据库隔离】</a><li><a href=\"../后端手册/WebSocket 实时通信.html\" class=sidebar-link>WebSocket 实时通信</a> <li><a href=\"../后端手册/异常处理（错误码）.html\" class=sidebar-link>异常处理（错误码）</a> <li><a href=\"../后端手册/参数校验、时间传参.html\" class=sidebar-link>参数校验、时间传参</a> <li><a href=\"../后端手册/分页实现.html\" class=sidebar-link>分页实现</a> <li><a href=\"../后端手册/VO 对象转换、数据翻译.html\" class=sidebar-link>VO 对象转换、数据翻译</a> <li><a href=\"../后端手册/文件存储（上传下载）.html\" class=sidebar-link>文件存储（上传下载）</a> <li><a href=\"../后端手册/Excel 导入导出.html\" class=sidebar-link>Excel 导入导出</a> <li><a href=\"../后端手册/操作日志、访问日志、异常日志.html\" class=sidebar-link>操作日志、访问日志、异常日志</a> <li><a href=\"../后端手册/MyBatis 数据库.html\" class=sidebar-link>MyBatis 数据库</a> <li><a href=\"../后端手册/MyBatis 联表&分页查询.html\" class=sidebar-link>MyBatis 联表&amp;分页查询</a> <li> <a href=\"../后端手册/多数据源（读写分离）、事务.html\" class=sidebar-link>多数据源（读写分离）、事务</a> <li><a href=\"../后端手册/Redis 缓存.html\" class=sidebar-link>Redis 缓存</a> <li><a href=\"../后端手册/本地缓存.html\" class=sidebar-link>本地缓存</a> <li><a href=\"../后端手册/异步任务.html\" class=sidebar-link>异步任务</a> <li><a href=\"../后端手册/分布式锁.html\" class=sidebar-link>分布式锁</a> <li><a href=\"../后端手册/幂等性（防重复提交）.html\" class=sidebar-link>幂等性（防重复提交）</a> <li><a href=\"../后端手册/请求限流（RateLimiter）.html\" class=sidebar-link>请求限流（RateLimiter）</a> <li><a href=\"../后端手册/单元测试.html\" class=sidebar-link>单元测试</a> <li><a href=\"../后端手册/验证码.html\" class=sidebar-link>验证码</a> <li><a href=\"../后端手册/工具类 Util.html\" class=sidebar-link>工具类 Util</a> <li><a href=\"../后端手册/配置管理.html\" class=sidebar-link>配置管理</a> <li><a href=\"../后端手册/数据库文档.html\" class=sidebar-link>数据库文档</a> </ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>中间件手册</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../中间件手册/定时任务.html\" class=sidebar-link>定时任务</a> <li><a href=\"../中间件手册/消息队列（内存）.html\" class=sidebar-link>消息队列（内存）</a> <li><a href=\"../中间件手册/消息队列（Redis）.html\" class=sidebar-link>消息队列（Redis）</a> <li><a href=\"../中间件手册/消息队列（RocketMQ）.html\" class=sidebar-link>消息队列（RocketMQ）</a> <li><a href=\"../中间件手册/消息队列（RabbitMQ）.html\" class=sidebar-link>消息队列（RabbitMQ）</a> <li><a href=\"../中间件手册/消息队列（Kafka）.html\" class=sidebar-link>消息队列（Kafka）</a> <li><a href=\"../中间件手册/限流熔断.html\" class=sidebar-link>限流熔断</a> </ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>工作流手册</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../工作流手册/工作流演示.html\" class=sidebar-link>工作流演示</a> <li><a href=\"../支付手册/功能开启.html\" class=sidebar-link>功能开启</a> <li><a href=\"../工作流手册/工作流（达梦适配）.html\" class=sidebar-link>工作流（达梦适配）</a> <li><a href=\"../工作流手册/审批接入（流程表单）.html\" class=sidebar-link>审批接入（流程表单）</a> <li><a href=\"../工作流手册/审批接入（业务表单）.html\" class=sidebar-link>审批接入（业务表单）</a> <li><a href=\"../工作流手册/流程设计器（BPMN）.html\" class=sidebar-link>流程设计器（BPMN）</a> <li><a href=\"../工作流手册/流程设计器（钉钉、飞书）.html\" class=sidebar-link>流程设计器（钉钉、飞书）</a> <li><a href=\"../工作流手册/选择审批人、发起人自选.html\" class=sidebar-link>选择审批人、发起人自选</a> <li><a href=\"../工作流手册/会签、或签、依次审批.html\" class=sidebar-link>会签、或签、依次审批</a> <li> <a href=\"../工作流手册/流程发起、取消、重新发起.html\" class=sidebar-link>流程发起、取消、重新发起</a> <li><a href=\"../工作流手册/审批通过、不通过、驳回.html\" class=sidebar-link>审批通过、不通过、驳回</a> <li><a href=\"../工作流手册/审批加签、减签.html\" class=sidebar-link>审批加签、减签</a> <li> <a href=\"../工作流手册/审批转办、委派、抄送.html\" class=sidebar-link>审批转办、委派、抄送</a> <li><a href=\"../工作流手册/执行监听器、任务监听器.html\" class=sidebar-link>执行监听器、任务监听器</a> <li><a href=\"../工作流手册/流程表达式.html\" class=sidebar-link>流程表达式</a> <li><a href=\"../工作流手册/流程审批通知.html\" class=sidebar-link>流程审批通知</a> </ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>大屏手册</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../大屏手册/报表设计器.html\" class=sidebar-link>报表设计器</a> <li><a href=\"../大屏手册/大屏设计器.html\" class=sidebar-link>大屏设计器</a> </ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>支付手册</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../支付手册/功能开启.html\" class=sidebar-link>功能开启</a> <li><a href=\"../支付手册/支付宝支付接入.html\" class=sidebar-link>支付宝支付接入</a> <li><a href=\"../支付手册/微信公众号支付接入.html\" class=sidebar-link>微信公众号支付接入</a> <li><a href=\"../支付手册/微信小程序支付接入.html\" class=sidebar-link>微信小程序支付接入</a> <li><a href=\"../支付手册/支付宝、微信退款接入.html\" class=sidebar-link>支付宝、微信退款接入</a> </ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>会员手册</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../支付手册/功能开启.html\" class=sidebar-link>功能开启</a> <li><a href=\"../会员手册/微信公众号登录.html\" class=sidebar-link>微信公众号登录</a> <li><a href=\"../会员手册/微信小程序登录.html\" class=sidebar-link>微信小程序登录</a> <li><a href=\"../会员手册/会员用户、标签、分组.html\" class=sidebar-link>会员用户、标签、分组</a> <li><a href=\"../会员手册/会员等级、积分、签到.html\" class=sidebar-link>会员等级、积分、签到</a> </ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>商城手册</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../商城手册/商城演示.html\" class=sidebar-link>商城演示</a> <li><a href=\"../支付手册/功能开启.html\" class=sidebar-link>功能开启</a> <li><a href=\"../商城手册/商城装修.html\" class=sidebar-link>商城装修</a> <li><a href=\"../商城手册/【商品】商品分类.html\" class=sidebar-link>【商品】商品分类</a> <li><a href=\"../商城手册/【商品】商品属性.html\" class=sidebar-link>【商品】商品属性</a> <li><a href=\"../商城手册/【商品】商品 SPU 与 SKU.html\" class=sidebar-link>【商品】商品 SPU 与 SKU</a> <li><a href=\"../商城手册/【商品】商品评价.html\" class=sidebar-link>【商品】商品评价</a> <li><a href=\"../商城手册/【交易】购物车.html\" class=sidebar-link>【交易】购物车</a> <li><a href=\"../商城手册/【交易】交易订单.html\" class=sidebar-link>【交易】交易订单</a> <li><a href=\"../商城手册/【交易】售后退款.html\" class=sidebar-link>【交易】售后退款</a> <li> <a href=\"../商城手册/【交易】快递发货.html\" class=sidebar-link>【交易】快递发货</a> <li><a href=\"../商城手册/【交易】门店自提.html\" class=sidebar-link>【交易】门店自提</a> <li><a href=\"../商城手册/【交易】分销返佣.html\" class=sidebar-link>【交易】分销返佣</a> <li><a href=\"../商城手册/【营销】优惠劵.html\" class=sidebar-link>【营销】优惠劵</a> <li><a href=\"../商城手册/【营销】拼团活动.html\" class=sidebar-link>【营销】拼团活动</a> <li><a href=\"../商城手册/【营销】秒杀活动.html\" class=sidebar-link>【营销】秒杀活动</a> <li><a href=\"../商城手册/【营销】砍价活动.html\" class=sidebar-link>【营销】砍价活动</a> <li><a href=\"../商城手册/【营销】满减送.html\" class=sidebar-link>【营销】满减送</a> <li><a href=\"../商城手册/【营销】限时折扣.html\" class=sidebar-link>【营销】限时折扣</a> <li><a href=\"../商城手册/【营销】内容管理.html\" class=sidebar-link>【营销】内容管理</a> <li><a href=\"../商城手册/【统计】会员、商品、交易统计.html\" class=sidebar-link>【统计】会员、商品、交易统计</a> </ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>ERP 手册</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../支付手册/ERP 演示.html.html\" class=sidebar-link>ERP 演示</a> <li><a href=\"../支付手册/功能开启.html\" class=sidebar-link>功能开启</a> <li><a href=\"../ERP 手册/【产品】产品信息、分类、单位.html\" class=sidebar-link>【产品】产品信息、分类、单位</a> <li><a href=\"../ERP 手册/【库存】产品库存、库存明细.html\" class=sidebar-link>【库存】产品库存、库存明细</a> <li><a href=\"../ERP 手册/【库存】其它入库、其它出库.html\" class=sidebar-link>【库存】其它入库、其它出库</a> <li> <a href=\"../ERP 手册/【库存】库存调拨、库存盘点.html\" class=sidebar-link>【库存】库存调拨、库存盘点</a> <li><a href=\"../ERP 手册/【采购】采购订单、入库、退货.html\" class=sidebar-link>【采购】采购订单、入库、退货</a> <li><a href=\"../ERP 手册/【销售】销售订单、出库、退货.html\" class=sidebar-link>【销售】销售订单、出库、退货</a> <li> <a href=\"../ERP 手册/【财务】采购付款、销售收款.html\" class=sidebar-link>【财务】采购付款、销售收款</a> </ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=\"sidebar-heading open\"><span>CRM 手册</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../CRM 手册/CRM 演示.html\" class=sidebar-link>CRM 演示</a> <li><a href=\"../支付手册/功能开启.html\" class=sidebar-link>功能开启</a> <li><a href=\"../CRM 手册/【线索】线索管理.html\" class=sidebar-link>【线索】线索管理</a> <li><a href=\"../CRM 手册/【客户】客户管理、公海客户.html\" class=sidebar-link>【客户】客户管理、公海客户</a> <li><a href=\"../CRM 手册/【商机】商机管理、商机状态.html\" class=sidebar-link>【商机】商机管理、商机状态</a> <li><a href=\"../CRM 手册/【合同】合同管理、合同提醒.html\" class=sidebar-link>【合同】合同管理、合同提醒</a> <li><a href=\"../CRM 手册/【回款】回款管理、回款计划.html\" class=sidebar-link>【回款】回款管理、回款计划</a> <li><a href=\"../CRM 手册/【产品】产品管理、产品分类.html\" class=\"active sidebar-link\" aria-current=page>【产品】产品管理、产品分类</a> <ul class=\"sidebar-sub-headers sf-hidden\"></ul> <li><a href=\"../CRM 手册/【通用】数据权限.html\" class=sidebar-link>【通用】数据权限</a> <li><a href=\"../CRM 手册/【通用】跟进记录、待办事项.html\" class=sidebar-link>【通用】跟进记录、待办事项</a> </ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>公众号手册</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../支付手册/功能开启.html\" class=sidebar-link>功能开启</a> <li><a href=\"../公众号手册/公众号接入.html\" class=sidebar-link>公众号接入</a> <li><a href=\"../公众号手册/公众号粉丝.html\" class=sidebar-link>公众号粉丝</a> <li><a href=\"../公众号手册/公众号标签.html\" class=sidebar-link>公众号标签</a> <li><a href=\"../公众号手册/公众号消息.html\" class=sidebar-link>公众号消息</a> <li><a href=\"../公众号手册/自动回复.html\" class=sidebar-link>自动回复</a> <li><a href=\"../公众号手册/公众号菜单.html\" class=sidebar-link>公众号菜单</a> <li><a href=\"../公众号手册/公众号素材.html\" class=sidebar-link>公众号素材</a> <li><a href=\"../公众号手册/公众号图文.html\" class=sidebar-link>公众号图文</a> <li><a href=\"../公众号手册/公众号统计.html\" class=sidebar-link>公众号统计</a> </ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>系统手册</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../系统手册/短信配置.html\" class=sidebar-link>短信配置</a> <li><a href=\"../系统手册/邮件配置.html\" class=sidebar-link>邮件配置</a> <li><a href=\"../系统手册/站内信配置.html\" class=sidebar-link>站内信配置</a> <li><a href=\"../系统手册/数据脱敏.html\" class=sidebar-link>数据脱敏</a> <li><a href=\"../系统手册/敏感词.html\" class=sidebar-link>敏感词</a> <li><a href=\"../系统手册/地区 & IP 库 _ ruoyi-vue-pro 开发指南 (2024_4_20 14_30_25).html\" class=sidebar-link>地区 &amp; IP 库</a> </ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>运维手册</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../运维手册/开发环境.html\" class=sidebar-link>开发环境</a> <li><a href=\"../运维手册/Linux 部署 _ ruoyi-vue-pro 开发指南 (2024_4_20 14_30_58).html\" class=sidebar-link>Linux 部署</a> <li><a href=\"../运维手册/Docker 部署 _ ruoyi-vue-pro 开发指南 (2024_4_20 14_31_12).html\" class=sidebar-link>Docker 部署</a> <li><a href=\"../运维手册/Jenkins 部署 _ ruoyi-vue-pro 开发指南 (2024_4_20 14_31_24).html\" class=sidebar-link>Jenkins 部署</a> <li><a href=\"../运维手册/HTTPS 证书 _ ruoyi-vue-pro 开发指南 (2024_4_20 14_31_35).html\" class=sidebar-link>HTTPS 证书</a> <li><a href=\"../运维手册/服务监控.html\" class=sidebar-link>服务监控</a> </ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>前端手册 Vue 3.x</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../前端手册 Vue 3.x/开发规范.html\" class=sidebar-link>开发规范</a> <li><a href=\"../前端手册 Vue 3.x/菜单路由.html\" class=sidebar-link>菜单路由</a> <li><a href=\"../前端手册 Vue 3.x/Icon 图标.html\" class=sidebar-link>Icon 图标</a> <li><a href=\"../前端手册 Vue 3.x/字典数据.html\" class=sidebar-link>字典数据</a> <li><a href=\"../前端手册 Vue 3.x/系统组件.html\" class=sidebar-link>系统组件</a> <li><a href=\"../前端手册 Vue 3.x/通用方法.html\" class=sidebar-link>通用方法</a> <li><a href=\"../前端手册 Vue 3.x/配置读取.html\" class=sidebar-link>配置读取</a> <li><a href=\"../前端手册 Vue 3.x/CRUD 组件.html/\" class=sidebar-link>CRUD 组件</a> <li><a href=\"../前端手册 Vue 3.x/国际化.html\" class=sidebar-link>国际化</a> <li><a href=\"../前端手册 Vue 3.x/IDE 调试.html\" class=sidebar-link>IDE 调试</a> <li><a href=\"../前端手册 Vue 3.x/代码格式化.html\" class=sidebar-link>代码格式化</a> </ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>前端手册 Vue 2.x</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"../前端手册 Vue 2.x/开发规范.html\" class=sidebar-link>开发规范</a> <li><a href=\"../前端手册 Vue 2.x/菜单路由.html\" class=sidebar-link>菜单路由</a> <li><a href=\"../前端手册 Vue 2.x/Icon 图标.html\" class=sidebar-link>Icon 图标</a> <li><a href=\"../前端手册 Vue 2.x/字典数据.html\" class=sidebar-link>字典数据</a> <li><a href=\"../前端手册 Vue 2.x/系统组件.html\" class=sidebar-link>系统组件</a> <li><a href=\"../前端手册 Vue 2.x/通用方法.html\" class=sidebar-link>通用方法</a> <li><a href=\"../前端手册 Vue 2.x/配置读取.html\" class=sidebar-link>配置读取</a> </ul> </section> <li> <section class=\"sidebar-group depth-0\"><p class=sidebar-heading><span>更新日志</span></p> <ul class=\"sidebar-links sidebar-group-items\"> <li><a href=\"https://doc.iocoder.cn/changelog/2.1.0/\" class=sidebar-link>【v2.1.0】开发中</a> <li><a href=\"https://doc.iocoder.cn/changelog/2.0.1/\" class=sidebar-link>【v2.0.1】2024-03-01</a> <li><a href=\"https://doc.iocoder.cn/changelog/2.0.0/\" class=sidebar-link>【v2.0.0】2024-01-26</a> <li><a href=\"https://doc.iocoder.cn/changelog/1.9.0/\" class=sidebar-link>【v1.9.0】2023-12-01</a> <li><a href=\"https://doc.iocoder.cn/changelog/1.8.3/\" class=sidebar-link>【v1.8.3】2023-10-24</a> </ul> </section> </ul>";
        String path = "D:\\table\\芋道\\单体文档--yudao-vue-pro\\yudao-vue-pro1";

        try {
            Path basePath = Paths.get(path);
            int replaceCount = 0;

            Files.walk(basePath)
                    .filter(Files::isRegularFile)
                    .filter(filePath -> filePath.toString().endsWith(".html"))
                    .forEach(filePath -> {
                        try {
                            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
                            if (lines.size() >= 17) {
                                String line17 = lines.get(16);
                                Pattern pattern = Pattern.compile("<aside[^>]*>.*?</aside>", Pattern.DOTALL);
                                Matcher matcher = pattern.matcher(line17);

                                if (matcher.find()) {
                                    String newLine17 = matcher.replaceAll("<aside>" + targetpath + "</aside>");
                                    lines.set(16, newLine17);
                                    Files.write(filePath, lines, StandardCharsets.UTF_8);
//                                    replaceCount++;
                                    System.out.println("已替换文件：" + filePath.getFileName());
                                } else {
                                    System.out.println("文件：" + filePath.getFileName() + " 第 17 行未找到 aside 标签");
                                }
                            } else {
                                System.out.println("文件：" + filePath.getFileName() + " 不足 17 行");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            System.out.println("\n处理完成！共替换 " + replaceCount + " 个文件");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private static void replacenewcontent() {
        String contentpath = "D:\\table\\芋道\\单体文档--yudao-vue-pro——old\\yudao-vue-pro\\sidebar.html";
        String path = "D:\\table\\芋道\\单体文档--yudao-vue-pro\\yudao-vue-pro1";
        try {
            String sidebarContent = new String(Files.readAllBytes(Paths.get(contentpath)), StandardCharsets.UTF_8);
            Pattern pattern = Pattern.compile("<aside[^>]*>(.*?)</aside>", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(sidebarContent);

            if (!matcher.find()) {
                System.out.println("未找到 aside 标签内容");
                return;
            }

            String contentside = matcher.group(1);
            System.out.println("已读取 aside 内容，长度：" + contentside.length());

            Path basePath = Paths.get(path);
            int replaceCount = 0;

            Files.walk(basePath)
                    .filter(Files::isRegularFile)
                    .filter(filePath -> filePath.toString().endsWith(".html"))
                    .forEach(filePath -> {
                        try {
                            String content = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
                            Pattern asidePattern = Pattern.compile("<aside[^>]*>.*?</aside>", Pattern.DOTALL);
                            String updatedContent = asidePattern.matcher(content).replaceAll(contentside);

                            if (!content.equals(updatedContent)) {
                                FileUtils.write(filePath.toFile(), updatedContent, StandardCharsets.UTF_8);
//                                replaceCount++;
                                System.out.println("已替换文件：" + filePath);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            System.out.println("\n处理完成！共替换 " + replaceCount + " 个文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void update1line() {
        String path = "D:\\table\\芋道\\单体文档--yudao-vue-pro——old\\yudao-vue-pro\\sidebar.html";
        try {
            Path filePath = Paths.get(path);
            String content = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
            String oneLineContent = content.replaceAll("\\r?\\n", "").replaceAll("\\s+", " ").trim();
            Files.write(filePath, oneLineContent.getBytes(StandardCharsets.UTF_8));
            System.out.println("已将文件合并为 1 行：" + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void updateSidebar() {
        String baseurl = "D:\\table\\芋道\\单体文档--yudao-vue-pro\\yudao-vue-pro";
        String sidebarpath = baseurl+"\\sidebar.html";
        try {
            Path basePath = Paths.get(baseurl);
            Map<String, String> fileMap = new HashMap<>();

            Files.walk(basePath)
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        String fileName = filePath.getFileName().toString();
                        int lastDotIndex = fileName.lastIndexOf('.');
                        String fileNameWithoutExtension = lastDotIndex != -1 ? fileName.substring(0, lastDotIndex) : fileName;
                        String relativePath = filePath.toString().replace(baseurl, "..").replace("\\", "/");
                        fileMap.put(fileNameWithoutExtension, relativePath);
                    });

//            fileMap.forEach((key, value) -> System.out.println(key + " -> " + value));

            String sidebarContent = new String(Files.readAllBytes(Paths.get(sidebarpath)), StandardCharsets.UTF_8);
            Pattern pattern = Pattern.compile("<a\\s+([^>]*href\\s*=\\s*(?:\"([^\"]*)\"|'([^']*)'|([^\\s>]+)))[^>]*>([^<]+)</a>");
            Matcher matcher = pattern.matcher(sidebarContent);
            StringBuffer sb = new StringBuffer();
            int replaceCount = 0;

            while (matcher.find()) {
                String fullMatch = matcher.group(0);
                String attributes = matcher.group(1);
                String hrefValue = matcher.group(2) != null ? matcher.group(2) : (matcher.group(3) != null ? matcher.group(3) : matcher.group(4));
                String linkText = matcher.group(5).trim();

                System.out.println("匹配到链接：");
                System.out.println("  链接文本：" + linkText);
                System.out.println("  旧路径：" + hrefValue);

                if (fileMap.containsKey(linkText)) {
                    String newPath = fileMap.get(linkText);
                    String newAttributes = attributes.replace(hrefValue, newPath);
                    String newLink = fullMatch.replace(attributes, newAttributes);
                    matcher.appendReplacement(sb, newLink);
                    System.out.println("  ✓ 已替换为新路径：" + newPath);
                    replaceCount++;
                } else {
                    matcher.appendReplacement(sb, fullMatch);
                    System.out.println("  ✗ fileMap 中不存在，跳过");
                }
                System.out.println();
            }
            matcher.appendTail(sb);

            System.out.println("\n总共匹配到 " + replaceCount + " 处需要替换的链接");
            Files.write(Paths.get(sidebarpath), sb.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println("sidebar.html 已更新完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void replaceName() {
        String path = "D:\\table\\芋道\\单体文档--yudao-vue-pro\\yudao-vue-pro";
        try {
            Path basePath = Paths.get(path);
            Files.walk(basePath)
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        try {
                            String fileName = filePath.getFileName().toString();
                            int firstUnderscoreIndex = fileName.indexOf('_');
                            int htmlIndex = fileName.lastIndexOf(".html");
                            String newFileName= fileName.replace(" ", "");
                            if (firstUnderscoreIndex != -1 && htmlIndex != -1 && firstUnderscoreIndex < htmlIndex) {
                                newFileName  = fileName.substring(0, firstUnderscoreIndex).replaceAll("\\s+", "") + ".html";

                            }
                            Path newFilePath = filePath.resolveSibling(newFileName);
                            Files.move(filePath, newFilePath);
                            System.out.println("重命名：" + fileName + " -> " + newFileName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFileName(){
        String path = "D:\\table\\芋道\\单体文档--yudao-vue-pro\\yudao-vue-pro";
        try {
            Path basePath = Paths.get(path);
            Files.walk(basePath)
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        Path relativePath = basePath.relativize(filePath);
                        String pathString = relativePath.toString().replace("\\", "/");
                        System.out.println("\"../" + pathString + "\"");
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void replaceSidebar(){
        String path = "D:\\table\\芋道\\单体文档--yudao-vue-pro\\yudao-vue-pro";
        try {
            Path basePath = Paths.get(path);
            Files.walk(basePath)
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        try {
                            String fileName = filePath.getFileName().toString();
                            if ("sidebar.html".equals(fileName)) {
                                return;
                            }
                            String content = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
                            String updatedContent = content.replaceAll("\\s*<aside[^>]*>.*?</aside>", "");
                            if (!content.equals(updatedContent)) {
                                FileUtils.write(filePath.toFile(), updatedContent, StandardCharsets.UTF_8);
                                System.out.println("已删除 aside 标签：" + filePath);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addQuotesToHref() {
        String path = "D:\\table\\芋道\\单体文档--yudao-vue-pro——old\\yudao-vue-pro";
        try {
            Path basePath = Paths.get(path);
            int fileCount = 0;

            Files.walk(basePath)
                    .filter(Files::isRegularFile)
                    .filter(filePath -> filePath.toString().endsWith(".html"))
                    .forEach(filePath -> {
                        try {
                            String content = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
                            Pattern pattern = Pattern.compile("<a\\s+([^>]*href\\s*=\\s*([^\"'\\s][^\\s>]*))");
                            Matcher matcher = pattern.matcher(content);
                            StringBuffer sb = new StringBuffer();
                            int count = 0;

                            while (matcher.find()) {
                                String fullMatch = matcher.group(0);
                                String attributes = matcher.group(1);
                                String hrefValue = matcher.group(2);

                                if (!hrefValue.startsWith("\"") && !hrefValue.startsWith("'")) {
                                    String newHref = "href=\"" + hrefValue + "\"";
                                    String newMatch = fullMatch.replace("href=" + hrefValue, newHref);
                                    matcher.appendReplacement(sb, newMatch);
                                    count++;
                                } else {
                                    matcher.appendReplacement(sb, fullMatch);
                                }
                            }
                            matcher.appendTail(sb);

                            String updatedContent = sb.toString();
                            if (!content.equals(updatedContent)) {
                                FileUtils.write(filePath.toFile(), updatedContent, StandardCharsets.UTF_8);
//                                System.out.println("已处理文件 [" + (++fileCount) + "]: " + filePath + " (修复 " + count + " 处)");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            System.out.println("\n处理完成！共处理 " + fileCount + " 个文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
