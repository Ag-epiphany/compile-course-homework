package lexical_analysis.util.io;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import lexical_analysis.token.type.LexType;
import org.apache.poi.ss.usermodel.FillPatternType;

public class LexTypeConverter implements Converter<LexType> {
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Class<?> supportJavaTypeKey() {
        return LexType.class;
    }

    @Override
    public WriteCellData<?> convertToExcelData(LexType value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new WriteCellData<String>(value.toString());
    }
}
