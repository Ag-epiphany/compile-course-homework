package lexical_analysis.token.util;

import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import lexical_analysis.util.dict.TokenColorDict;
import org.apache.poi.ss.usermodel.*;

import java.util.Iterator;

public class TokenColorRowWriteHandler implements RowWriteHandler {
    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                Row row, Integer relativeRowIndex, Boolean isHead) {
        if (isHead) {
            return;
        }

        CellStyle cellStyle = getRowColor(row);
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            cell.setCellStyle(cellStyle);
        }
    }

    private CellStyle getRowColor(Row row) {
        Workbook workbook = row.getSheet().getWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();

        String lexTypeName = row.getCell(1).getStringCellValue();
        IndexedColors color = TokenColorDict.getIndexedColor(lexTypeName);

        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(color.getIndex());

        return cellStyle;
    }
}
