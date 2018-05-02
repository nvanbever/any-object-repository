package be.nvb.aor.infrastructure.aws.port.rest;

import be.nvb.aor.application.view.ContactDataView;
import be.nvb.aor.application.view.PersonView;
import be.nvb.aor.application.view.ProductCatalogView;
import be.nvb.aor.application.view.SupplierView;
import be.nvb.aor.infrastructure.port.rest.SpringSupplierApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Route;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static spark.Spark.*;

public class SparkResources {
    public static void defineResources(Supplier<Consumer<String>> logger) {
        crossOrigin();
        logRequest(logger);

        path("/suppliers", () -> {
            get("/:supplierId/catalogs/:productCatalogId", findProductCatalogById());
            get("/:supplierId/catalogs", findProductCatalogs());
            post("/:supplierId/catalogs", createProductCatalog());
            put("/:supplierId/catalogs/:productCatalogId", modifyProductCatalog());
            delete("/:supplierId/:productCatalogId", deleteProductCatalog());
            get("/", findAllSuppliers());
            post("/", createSupplier());
            put("/:supplierId", modifySupplier());
            post("/:supplierId/contacts", addSupplierContact());
        });
    }

    private static Route addSupplierContact() {
        return (request, response) -> SpringSupplierApi.addContact(
                parameter(request, "supplierId", true),
                new ObjectMapper().readValue(request.body(), PersonView.class)
        );
    }

    private static Route modifySupplier() {
        return (request, response) -> SpringSupplierApi.modifySupplier(
                parameter(request, "supplierId", true),
                new ObjectMapper().readValue(request.body(), SupplierView.class)
        );
    }

    private static Route createSupplier() {
        return (request, response) -> SpringSupplierApi.createSupplier(new ObjectMapper().readValue(request.body(), SupplierView.class));
    }

    private static Route findAllSuppliers() {
        return (request, response) -> SpringSupplierApi.findAllSuppliers();
    }

    private static Route deleteProductCatalog() {
        return (request, response) -> SpringSupplierApi.deleteProductCatalog(
                parameter(request, "supplierId", true),
                parameter(request, "productCatalogId", true)
        );
    }

    private static Route modifyProductCatalog() {
        return (request, response) -> SpringSupplierApi.modifyProductCatalog(
                parameter(request, "supplierId", true),
                parameter(request, "productCatalogId", true),
                new ObjectMapper().readValue(request.body(), ProductCatalogView.class)
        );
    }

    private static Route createProductCatalog() {
        return (request, response) -> SpringSupplierApi.createProductCatalog(
                parameter(request, "supplierId", true),
                new ObjectMapper().readValue(request.body(), ProductCatalogView.class)
        );
    }

    private static Route findProductCatalogs() {
        return (request, response) -> SpringSupplierApi.findProductCatalogs(
                parameter(request, "supplierId", true),
                requestParameter(request, "name", false));
    }

    private static Route findProductCatalogById() {
        return (request, response) -> SpringSupplierApi.findProductCatalogById(
                parameter(request, "supplierId", true),
                parameter(request, "productCatalogId", true));
    }

    private static void logRequest(Supplier<Consumer<String>> logger, Request request) {
        log(logger, String.format("request attributes %s.", request.attributes()));
        log(logger, String.format("request query parameters %s.", request.queryParams()));
        log(logger, String.format("request parameters %s.", request.params()));
    }

    private static void log(Supplier<Consumer<String>> logger, String text) {
        logger.get().accept(text);
    }

    private static void logRequest(Supplier<Consumer<String>> logger) {
        before((request, response) -> log(logger, String.format("before request path info %s, servlet path %s.", request.pathInfo(), request.servletPath())));
        after((request, response) -> log(logger, String.format("after request %s, servlet path %s.", request.pathInfo(), request.servletPath())));
    }

    private static void crossOrigin() {
        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }

    private static Integer intParameter(Request request, String parameterName, boolean required) {
        String parameterValue = parameter(request, parameterName, required);

        try {
            return Integer.parseInt(parameterValue);
        } catch (NumberFormatException e) {
            throw new RuntimeException(String.format("Parameter %s must be an integer and was %s.", parameterName, parameterValue), e);
        }
    }

    private static String parameter(Request request, String parameterName, boolean required) {
        String parameterValue = request.params(parameterName.toLowerCase());

        if (parameterValue != null) {
            return parameterValue;
        } else if (required) {
            throw new RuntimeException(String.format("Parameter %s is required.", parameterName));
        }

        return null;
    }

    private static String requestParameter(Request request, String parameterName, boolean required) {
        String parameterValue = request.queryParams(parameterName.toLowerCase());

        if (parameterValue != null) {
            return parameterValue;
        } else if (required) {
            throw new RuntimeException(String.format("Parameter %s is required.", parameterName));
        }

        return null;
    }
}
