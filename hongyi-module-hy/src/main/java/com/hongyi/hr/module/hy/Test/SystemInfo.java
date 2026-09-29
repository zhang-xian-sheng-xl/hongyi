package com.hongyi.hr.module.hy.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;


public class SystemInfo {

    private static final int CPU_CORE = Runtime.getRuntime().availableProcessors();



    public static void main(String[] args) {
        System.out.println("==================== 系统信息详情 ====================");
         Integer count=0;

        // 1. CPU信息
        System.out.println("\n【CPU信息】");
        System.out.println("CPU核心数: " + CPU_CORE);
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println("系统名称: " + osBean.getName());
        System.out.println("系统架构: " + osBean.getArch());
        System.out.println("系统版本: " + osBean.getVersion());

        // 2. 内存信息
        System.out.println("\n【JVM内存信息】");
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long maxMemory = runtime.maxMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("JVM总内存: " + formatBytes(totalMemory));
        System.out.println("JVM已用内存: " + formatBytes(usedMemory));
        System.out.println("JVM空闲内存: " + formatBytes(freeMemory));
        System.out.println("JVM最大内存: " + formatBytes(maxMemory));
        System.out.println("内存使用率: " + String.format("%.2f", (usedMemory * 100.0 / totalMemory)) + "%");

        // 3. 操作系统信息
        System.out.println("\n【操作系统信息】");
        System.out.println("操作系统: " + System.getProperty("os.name"));
        System.out.println("系统版本: " + System.getProperty("os.version"));
        System.out.println("系统架构: " + System.getProperty("os.arch"));
        System.out.println("当前用户: " + System.getProperty("user.name"));
        System.out.println("用户主目录: " + System.getProperty("user.home"));
        System.out.println("工作目录: " + System.getProperty("user.dir"));

        // 4. JVM信息
        System.out.println("\n【JVM信息】");
        System.out.println("Java版本: " + System.getProperty("java.version"));
        System.out.println("Java供应商: " + System.getProperty("java.vendor"));
        System.out.println("Java安装目录: " + System.getProperty("java.home"));
        System.out.println("类路径: " + System.getProperty("java.class.path"));
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("JVM进程ID: " + jvmName.split("@")[0]);
        System.out.println("JVM启动时间: " + new Date(ManagementFactory.getRuntimeMXBean().getStartTime()));
        System.out.println("JVM运行时长: " + formatDuration(ManagementFactory.getRuntimeMXBean().getUptime()));

        // 5. 网络信息
        System.out.println("\n【网络信息】");
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("主机名: " + localHost.getHostName());
            System.out.println("IP地址: " + localHost.getHostAddress());

            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
            if (networkInterface != null) {
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    StringBuilder macBuilder = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        macBuilder.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    System.out.println("MAC地址: " + macBuilder.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("获取网络信息失败: " + e.getMessage());
        }

        // 6. 线程信息
        System.out.println("\n【线程信息】");
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        System.out.println("活动线程数: " + threadMXBean.getThreadCount());
        System.out.println("峰值线程数: " + threadMXBean.getPeakThreadCount());
        System.out.println("守护线程数: " + threadMXBean.getDaemonThreadCount());
        System.out.println("总启动线程数: " + threadMXBean.getTotalStartedThreadCount());

        // 7. 磁盘信息
        System.out.println("\n【磁盘信息】");
        java.io.File[] roots = java.io.File.listRoots();
        for (java.io.File root : roots) {
            long totalSpace = root.getTotalSpace();
            long freeSpace = root.getFreeSpace();
            long usableSpace = root.getUsableSpace();
            long usedSpace = totalSpace - freeSpace;

            System.out.println("磁盘: " + root.getPath());
            System.out.println("  总空间: " + formatBytes(totalSpace));
            System.out.println("  已用空间: " + formatBytes(usedSpace));
            System.out.println("  可用空间: " + formatBytes(usableSpace));
            System.out.println("  使用率: " + String.format("%.2f", (usedSpace * 100.0 / totalSpace)) + "%");
        }

        System.out.println("\n====================================================");
    }

    private static String formatBytes(long bytes) {
        if (bytes <= 0) return "0 B";
        String[] units = {"B", "KB", "MB", "GB", "TB"};
        int unitIndex = (int) (Math.log10(bytes) / Math.log10(1024));
        double value = bytes / Math.pow(1024, unitIndex);
        return String.format("%.2f %s", value, units[unitIndex]);
    }

    private static String formatDuration(long milliseconds) {
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (days > 0) {
            return String.format("%d天%d小时%d分钟", days, hours % 24, minutes % 60);
        } else if (hours > 0) {
            return String.format("%d小时%d分钟", hours, minutes % 60);
        } else if (minutes > 0) {
            return String.format("%d分钟%d秒", minutes, seconds % 60);
        } else {
            return String.format("%d秒", seconds);
        }
    }
}
