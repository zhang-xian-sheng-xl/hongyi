import {useUserStore} from "@/store/modules/user";
import dayjs from "dayjs";
import {useLocaleStoreWithOut} from "@/store/modules/locale";
const { t } = useI18n() // 国际化

/**
 * 统一返回返回一个字符串5A20
 * @returns 字符串 "5A20"
 */
export const getcompanyCode="5A20";
export const ci=t('common.inputText')+' ';
export const cs= t('common.selectText')+' ';
export const cn= t('common.notnull')+' ';
const userStore = useUserStore()
export const today = dayjs().format("YYYY-MM-DD")
export const curttrnYear = dayjs().format("YYYY")
export const todayTime = dayjs().format("YYYY-MM-DD HH:mm:ss")
export const todayTimeStart = dayjs().startOf('day').format("YYYY-MM-DD HH:mm:ss")

export const todayTimeEnd = dayjs().endOf('day').format("YYYY-MM-DD HH:mm:ss")
export const  getCurttenUserInfo=()=>{
  return userStore.getUser;
}

//将字符串转时间戳
export const transTimestamp = (time: string) => {
  if (time) {
    return new Date(time).getTime();
  }
  return time;
}
//将时间戳转换为年月日时分秒
export const formatTimestamp = (timestamp: number | string) => {
  if (!timestamp) {
    return timestamp;
  }

  const date = new Date(Number(timestamp));
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  const seconds = String(date.getSeconds()).padStart(2, '0');

  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}
export const getcurttenymd = () => {

  const date = new Date();
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');


  return `${year}-${month}-${day} `;
}
export const week=()=>{
  const date = new Date();
  const week=date.getDay()
  return t(`week.week${week}`)
  //todo 获取今天是周几 0 周日 1 周一 以此类推
}

//将上传图片的路径转换为字符串
export const pictureUrlToString = (url: any) => {
  if (url != null) {
    url = Array.isArray(url)
      ? url.join(',')
      : url.toString();
  }
  return url
}

export const listToString = (url: any) => {
  return pictureUrlToString( url);
}
export const stringToList = (url: any) => {
  if(url!=null&&url.length>0){
    url = url.split(',');
  }
  return url;
}

export const button = (label: string) => {
  return  t(`button.${label}`);
}
export const button_ = (label: string) => {
  return  t(`button.${label}`);
}
export const common = (label: string) => {
  return  t(`common.${label}`);
}
export const action = (label: string) => {
  return  t(`action.${label}`);
}

export const issbtz_admin = () => {
  const sbtz_admin = userStore.getRoles?.some(role => role === 'sbtz_admin')
  return  sbtz_admin;
}
export const istool_admin = () => {
  const tool_admin = userStore.getRoles?.some(role => role === 'tool_admin')
  return  tool_admin;
}
export const  iscreator=(scope:any)=>{
  if(scope.row.creatorId!=undefined){
    if(scope.row.creatorId==getCurttenUserInfo().id){
      return  true
    }
  }else if(scope.row.creator!=undefined){
    if(scope.row.creator==getCurttenUserInfo().id){
      return  true
    }
  }else {
    return false
  }
}

export const lang = useLocaleStoreWithOut().getCurrentLocale.lang;
/**
 * 移除HTML标签
 * @param html HTML字符串
 * @returns 清理后的文本
 */
export const removeHtmlTags = (html: string) => {
  if (!html) return ''
  return html.replace(/<[^>]*>/g, '')
}
export  const formatOverhualName = (formData: any) => {
// 将formData.type 转为数字
formData.type = parseInt(formData.type) || 0;
  let resultname = ''
  const prefix= `${formData.year}年${formData.unit}号机组${formData.level}`;
  const affix= ` ${formData.level} Maintenance of #Unit ${formData.unit} ${formData.professionl}`;
  switch (formData.type) {
    case 61:
      resultname = `级总体要求 General Requirements for Class `
      break
    case 62:
      resultname = `检修组织机构及职责 Maintenance organization and responsibilities `
      break
    case 63:
      resultname = `检修准备工作计划 Maintenance Preparation Work Plan`
      break
    case 64:
      resultname = `修前检测、评估与分析 Inspection and evaluation before repair `
      break

    case 65:
      resultname = `修前运行分析报告 Pre-repair operation analysis report `
      break
    case 66:
      resultname = `级检修修前检修分析报告 Pre-maintenance Maintenance Analysis Report for Class `
      break
    case 67:
      resultname = `技术监督计划  Technical Supervision Plan`
      break

    case 68:
      resultname = `检修项目编制与审核 Preparation and review of maintenance items`
      break
    case 69:
      resultname = `外包合同及物资需求  Outsourcing Contract and Material Requirements`
      break
    case 610:
      resultname = `修前性能试验 Performance test before repair `
      break
    case 611:
      resultname = `检修目标制定 Maintenance objective formulation `
      break
    case 612:
      resultname = `检修工期控制  Maintenance duration control`
      break
    case 6131:
      resultname = `检修文件包 Maintenance Document Package `
      break
    case 6132:
      resultname = `重点项目技术措施或方案 Technical measures or schemes for key projects `
      break
    case 6133:
      resultname = `检修安全措施 Maintenance safety measures `
      break
    case 6134:
      resultname = `其他 Other `
      break

    case 614:
      resultname = ` 检修项目发布及调整 Release and adjustment of maintenance items`
      break
    case 615:
      resultname = `检修考核  Maintenance assessment`
      break
    case 616:
      resultname = `检修项目招标与管理  Bidding and Management of Maintenance Project`
      break
    case 617:
      resultname = `工器具及检修辅助设施准备 Preparation of tools and auxiliary maintenance facilities `
      break
    case 618:
      resultname = `检修管理手册 Maintenance Management Manual `
      break
    case 619:
      resultname = `修前准备检查会 Pre-repair preparation inspection meeting `
      break
    case 620:
      resultname = `检修监理  Overhaul Supervision`
      break
    case 621:
      resultname = `其他 Other`
      break
    case 622:
      resultname = `整理最终缺陷 Finishing Final Defects`
      break
    case 71:
      resultname = `总体要求  General requirements`
      break
    case 72:
      resultname = `检修开工准备 Maintenance Commencement Preparation `
      break
    case 731:
      resultname = ` 检修例会 Regular Maintenance Meeting `
      break
    case 732:
      resultname = `解体分析会 Disintegration Analysis Meeting `
      break
    case 733:
      resultname = `专题会 Thematic meeting `
      break
    case 734:
      resultname = `检修项目变更 Change of overhaul items`
      break
    case 735:
      resultname = `主设备装复前确认 Confirmation before installation of main equipment`
      break
    case 736:
      resultname = `设备移动管理  Device Mobility Management`
      break
    case 74:
      resultname = `检修安全管理 Maintenance safety management`
      break
    case 75:
      resultname = `检修质量监督 Overhaul quality supervision`
      break
    case 76:
      resultname = `检修工期控制 Maintenance duration control`
      break
    case 77:
      resultname = `分部试运 Partial commissioning`
      break
    case 781:
      resultname = `冷态验收评价表 Cold Acceptance Evaluation Table `
      break
    case 782:
      resultname = `检修交待书（专业） Maintenance instructions (professional)`
      break
    case 79:
      resultname = `整套启动试验 Complete set of start-up tests`
      break
    case 710:
      resultname = `整套启动试运 Complete set of start-up commissioning`
      break
    case 711:
      resultname = `检修竣工 Overhaul completion`
      break
    case 81:
      resultname = `总体要求 General requirements`
      break
    case 82:
      resultname = `修后性能试验 Performance test after repair`
      break
    case 83:
      resultname = `热态评价 thermal evaluation`
      break
    case 84:
      resultname = `检修总结 Maintenance Summary`
      break
    case 85:
      resultname = `检修文件整理 Overhaul document arrangement`
      break
    case 861:
      resultname = `A/B级检修后评价工作 Post-overhaul evaluation of Class A/B`
      break
    case 862:
      resultname = `“全优”机组评选 “All excellent“ unit selection `
      break
    case 863:
      resultname = `《火电机组A/B级修后评价表》上报 Report of Grade A/B Post-repair Evaluation Form for Thermal Power Units `
      break
    case 864:
      resultname = `区域公司对电厂机组A/B修后评价 Post-repair Evaluation of Power Plant Unit A/B by Regional Company`
      break
    case 865:
      resultname = `A_B 级检修评价纳入年度绩效考核  A_B-level maintenance evaluation is included in the annual performance appraisal.`
      break

    case 91:
      resultname = `状态检修 Condition-based maintenance`
      break
  }

  return prefix+resultname+affix;
}



