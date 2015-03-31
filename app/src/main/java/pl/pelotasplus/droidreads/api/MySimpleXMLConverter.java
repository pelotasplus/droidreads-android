package pl.pelotasplus.droidreads.api;

import android.util.Log;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.RegistryMatcher;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.Date;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * A {@link Converter} which uses SimpleXML for reading and writing entities.
 *
 * @author Fabien Ric (fabien.ric@gmail.com)
 */
public class MySimpleXMLConverter implements Converter {
    private static final boolean DEFAULT_STRICT = true;
    private static final String CHARSET = "UTF-8";
    private static final String MIME_TYPE = "application/xml; charset=" + CHARSET;

    private final Serializer serializer;

    private final boolean strict;

    public MySimpleXMLConverter() {
        strict = DEFAULT_STRICT;

        RegistryMatcher m = new RegistryMatcher();
        m.bind(Date.class, new DateTransform()); //format));

        this.serializer = new Persister(m);
    }

    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        try {
            InputStream inputStream;

            if (false) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = body.in().read(buffer)) > -1) {
                    baos.write(buffer, 0, len);
                    Log.d("XXX", new String(buffer));
                }
                baos.flush();
                inputStream = new ByteArrayInputStream(baos.toByteArray());
            } else {
                inputStream = body.in();
            }

            return serializer.read((Class<?>) type, inputStream, strict);
        } catch (Exception e) {
            throw new ConversionException(e);
        }
    }

    public Object fromString(String string, Type type) throws ConversionException {
        try {
            return serializer.read((Class<?>) type, string, strict);
        } catch (Exception e) {
            throw new ConversionException(e);
        }
    }

    @Override
    public TypedOutput toBody(Object source) {
        OutputStreamWriter osw = null;

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            osw = new OutputStreamWriter(bos, CHARSET);
            serializer.write(source, osw);
            osw.flush();
            return new TypedByteArray(MIME_TYPE, bos.toByteArray());
        } catch (Exception e) {
            throw new AssertionError(e);
        } finally {
            try {
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
    }

    public boolean isStrict() {
        return strict;
    }
}