package dev.fxtech.llm_resume_generator.common.handler;

import dev.fxtech.llm_resume_generator.common.exception.FXErrno;
import dev.fxtech.llm_resume_generator.common.exception.FXException;
import dev.fxtech.llm_resume_generator.common.security.FXTokenCryptoUtil.FXEncryptedToken;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

@MappedTypes(FXEncryptedToken.class)
@MappedJdbcTypes({JdbcType.VARCHAR})
public class FXEncryptedTokenTypeHandler extends BaseTypeHandler<FXEncryptedToken> {

    private static final String SEPARATOR = "|||";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, FXEncryptedToken parameter, JdbcType jdbcType) {

        try {
            ps.setString(i, parameter.encryptedToken() + SEPARATOR + parameter.activeKeyVersion());
        } catch (SQLException se) {
            throw new FXException(FXErrno.DATABASE_ERROR, "SQL Error at FXEncryptedTokenTypeHandler.setNonNullParameter", se);
        }

    }

    @Override
    public FXEncryptedToken getNullableResult(ResultSet rs, String columnName) {

        try {
            return deserialize(rs.getString(columnName));
        } catch (SQLException se) {
            throw new FXException(FXErrno.DATABASE_ERROR, "SQL Error at FXEncryptedTokenTypeHandler.getNullableResult(ResultSet, String)", se);
        }

    }

    @Override
    public FXEncryptedToken getNullableResult(ResultSet rs, int columnIndex) {

        try {
            return deserialize(rs.getString(columnIndex));
        } catch (SQLException se) {
            throw new FXException(FXErrno.DATABASE_ERROR, "SQL Error at FXEncryptedTokenTypeHandler.getNullableResult(ResultSet, int)", se);
        }

    }

    @Override
    public FXEncryptedToken getNullableResult(CallableStatement cs, int columnIndex) {

        try {
            return deserialize(cs.getString(columnIndex));
        } catch (SQLException se) {
            throw new FXException(FXErrno.DATABASE_ERROR, "SQL Error at FXEncryptedTokenTypeHandler.getNullableResult(CallableStatement, int)", se);
        }

    }

    private FXEncryptedToken deserialize(String dbValue) {

        if (dbValue == null || dbValue.isEmpty()) return null;

        String[] parts = dbValue.split(Pattern.quote(SEPARATOR), 2);
        if (parts.length == 2) {
            return new FXEncryptedToken(parts[0], parts[1]);
        }

        return null;

    }

}
