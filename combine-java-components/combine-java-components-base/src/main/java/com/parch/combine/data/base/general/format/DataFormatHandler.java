package com.parch.combine.data.base.general.format;

import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.tools.variable.DataFindHandler;
import com.parch.combine.data.base.general.format.func.ClearDuplicateFormat;
import com.parch.combine.data.base.general.format.func.GroupFormat;
import com.parch.combine.data.base.general.format.func.ICustomFormat;
import com.parch.combine.data.base.general.format.func.JSONFormat;
import com.parch.combine.data.base.general.format.func.MD5Format;
import com.parch.combine.data.base.general.format.func.RangFormat;
import com.parch.combine.data.base.general.format.func.SortFormat;
import com.parch.combine.data.base.general.format.func.TextConvertFormat;
import com.parch.combine.data.base.general.format.func.ToMapFormat;
import com.parch.combine.data.base.general.format.func.ToTreeFormat;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据格式化处理器
 */
public class DataFormatHandler {

    /**
     * 格式化函数缓存
     */
    private static Map<String, ICustomFormat> formatFunctionMap = new HashMap<>();

    /**
     * 获取执行函数对象
     *
     * @param function 函数名称
     * @param params 参数
     * @return 函数
     */
    public static List<String> checkParams(DataFormatFunctionEnum function, String[] params) {
        ICustomFormat format = getFormatObj(function, params);
        if (format == null) {
            return Collections.singletonList("未知的函数类型");
        }

        return format.check(params);
    }

    /**
     * 获取执行函数对象
     *
     * @param function 函数名称
     * @param params 参数
     * @return 函数
     */
    public static DataFindHandler.GetDataFunction<Object> getFunction(DataFormatFunctionEnum function, String[] params) {
        ICustomFormat format = getFormatObj(function, params);
        if (format == null) {
            ComponentErrorHandler.print(function + " 函数不存在");
            return null;
        }

        final ICustomFormat finalFormat = format;
        return sourceData ->  finalFormat.format(sourceData, params);
    }

    /**
     * 获取格式化函数对象
     *
     * @param function 函数类型
     * @param params 参数集合
     * @return 格式化函数实例
     */
    private static ICustomFormat getFormatObj(DataFormatFunctionEnum function, String[] params) {
        ICustomFormat format;
        if (function == DataFormatFunctionEnum.CUSTOM) {
            String functionName = params[0];
            format = formatFunctionMap.get(functionName);
            if (format == null) {
                format = loadClass(params[0]);
                formatFunctionMap.put(functionName, format);
            }
        } else {
            format = formatFunctionMap.get(function.name());
            if (format == null) {
                switch (function) {
                    case JSON:
                        format = new JSONFormat();
                        break;
                    case SORT:
                        format = new SortFormat();
                        break;
                    case TEXT_CONVERT:
                        format = new TextConvertFormat();
                        break;
                    case GROUP:
                        format = new GroupFormat();
                        break;
                    case TO_MAP:
                        format = new ToMapFormat();
                        break;
                    case TO_TREE:
                        format = new ToTreeFormat();
                        break;
                    case RANG:
                        format = new RangFormat();
                        break;
                    case CLEAR_DUPLICATE:
                        format = new ClearDuplicateFormat();
                        break;
                    case MD5:
                        format = new MD5Format();
                    default:
                        break;
                }
                formatFunctionMap.put(function.name(), format);
            }
        }

        return format;
    }

    /**
     * 根据路径加载类
     *
     * @param classPath 类路径
     * @return 类实例
     */
    private static ICustomFormat loadClass(String classPath) {
        try {
            Class<?> clazz = Class.forName(classPath);
            Class<?>[] interfaces = clazz.getInterfaces();
            boolean isImplement = false;
            if (interfaces.length > 0) {
                for (Class<?> anInterface : interfaces) {
                    if (ICustomFormat.class == anInterface) {
                        isImplement = true;
                        break;
                    }
                }
            }
            if (!isImplement) {
                ComponentErrorHandler.print("自定义实现类未实现指定接口");
                return null;
            }

            return (ICustomFormat) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            ComponentErrorHandler.print("自定义格式化实现初始化失败", e);
        }

        return null;
    }
}
