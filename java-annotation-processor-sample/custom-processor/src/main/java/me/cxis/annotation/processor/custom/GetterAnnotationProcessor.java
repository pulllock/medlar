package me.cxis.annotation.processor.custom;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@SupportedAnnotationTypes({"me.cxis.annotation.processor.custom.Getter"})
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class GetterAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return true;
        }
        for (Element clazz : roundEnv.getElementsAnnotatedWith(Getter.class)) {
            if (!(clazz instanceof TypeElement)) {
                continue;
            }
            TypeElement type = (TypeElement) clazz;
            for (VariableElement field : ElementFilter.fieldsIn(type.getEnclosedElements())) {
                print(field.toString());
            }

            JavaFileObject builderFile = null;
            try {
                builderFile = processingEnv.getFiler().createClassFile(type.getQualifiedName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
                out.println("test");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    private void print(String message) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
    }
}
