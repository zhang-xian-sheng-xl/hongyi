@echo off
chcp 65001 >nul
echo ==============================================
echo          Git 远程仓库快速切换工具
echo ==============================================
echo 请选择要切换到的仓库：
echo.
echo 1 - 切换到 Gitee
echo 2 - 切换到 GitHub
echo.
set /p "choice=请输入数字 1 或 2："

if "%choice%"=="1" (
    git remote set-url origin https://gitee.com/zxl1473488107qqcom/hongyi.git
    echo.
    echo 已切换到 → Gitee
) else if "%choice%"=="2" (
    git remote set-url origin https://github.com/zhang-xian-sheng-xl/hongyi.git
    echo.
    echo 已切换到 → GitHub
) else (
    echo 输入错误！
)

echo.
echo 当前远程地址：
git remote -v
echo.
pause