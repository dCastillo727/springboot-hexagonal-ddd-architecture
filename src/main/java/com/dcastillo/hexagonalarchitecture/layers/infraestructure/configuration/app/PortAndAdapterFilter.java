package com.dcastillo.hexagonalarchitecture.layers.infraestructure.configuration.app;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.adapter.DrivenAdapter;
import com.dcastillo.hexagonalarchitecture.common.utils.annotations.adapter.DriverAdapter;
import com.dcastillo.hexagonalarchitecture.common.utils.annotations.port.DrivenPort;
import com.dcastillo.hexagonalarchitecture.common.utils.annotations.port.DriverPort;
import com.dcastillo.hexagonalarchitecture.common.utils.exceptions.app.AdapterNeedsPortException;
import com.dcastillo.hexagonalarchitecture.common.utils.exceptions.app.IllegalPortAdapterTypesCombination;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AbstractTypeHierarchyTraversingFilter;

import java.io.IOException;

public class PortAndAdapterFilter extends AbstractTypeHierarchyTraversingFilter {
    protected PortAndAdapterFilter() {
        super(false, false);
    }

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        String className = metadataReader.getClassMetadata().getClassName();

        boolean isDrivenPort = checkInterfacesAnnotatedWith(DrivenPort.class.getName(), annotationMetadata, metadataReaderFactory);
        boolean isDriverPort = checkInterfacesAnnotatedWith(DriverPort.class.getName(), annotationMetadata, metadataReaderFactory);
        boolean isDrivenAdapter = annotationMetadata.hasAnnotation(DrivenAdapter.class.getName());
        boolean isDriverAdapter = annotationMetadata.hasAnnotation(DriverAdapter.class.getName());

        if (isDriverPort && isDrivenAdapter || isDrivenPort && isDriverAdapter)
            throw new IllegalPortAdapterTypesCombination(className);

        if (!isDriverPort && isDriverAdapter || !isDrivenPort && isDrivenAdapter)
            throw new AdapterNeedsPortException(className);

        return false;
    }

    private boolean checkInterfacesAnnotatedWith(
            String name,
            AnnotationMetadata annotationMetadata,
            MetadataReaderFactory readerFactory
    ) {
        if (annotationMetadata.hasAnnotation(name)) {
            return true;
        }

        for (String interfaceName : annotationMetadata.getInterfaceNames()) {
            try {
                AnnotationMetadata interfaceMetadata = readerFactory
                        .getMetadataReader(interfaceName)
                        .getAnnotationMetadata();

                if (interfaceMetadata.hasAnnotation(name)) {
                    return true;
                }
            } catch (Exception _) {}
        }

        return false;
    }
}
