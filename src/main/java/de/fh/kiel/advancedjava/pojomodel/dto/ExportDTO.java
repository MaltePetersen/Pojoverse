package de.fh.kiel.advancedjava.pojomodel.dto;
import de.fh.kiel.advancedjava.pojomodel.model.Package;

import de.fh.kiel.advancedjava.pojomodel.model.Pojo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(example = "{\n" +
        "  \"pojoList\": [\n" +
        "    {\n" +
        "      \"emptyHull\": false,\n" +
        "      \"completePath\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitives\",\n" +
        "      \"className\": \"AnotherClassWithPrimitives\",\n" +
        "      \"attributes\": [\n" +
        "        {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitivesid\",\n" +
        "          \"name\": \"id\",\n" +
        "          \"accessModifier\": \"\",\n" +
        "          \"genericType\": null,\n" +
        "          \"clazz\": {\n" +
        "            \"emptyHull\": true,\n" +
        "            \"completePath\": \"java.lang.Integer\",\n" +
        "            \"className\": \"Integer\",\n" +
        "            \"attributes\": [],\n" +
        "            \"parentClass\": null,\n" +
        "            \"interfaces\": [],\n" +
        "            \"apackage\": {\n" +
        "              \"id\": \"java.lang\",\n" +
        "              \"name\": \"lang\",\n" +
        "              \"subPackage\": null\n" +
        "            }\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitivesis\",\n" +
        "          \"name\": \"is\",\n" +
        "          \"accessModifier\": \"\",\n" +
        "          \"genericType\": null,\n" +
        "          \"clazz\": {\n" +
        "            \"emptyHull\": true,\n" +
        "            \"completePath\": \"java.lang.Boolean\",\n" +
        "            \"className\": \"Boolean\",\n" +
        "            \"attributes\": [],\n" +
        "            \"parentClass\": null,\n" +
        "            \"interfaces\": [],\n" +
        "            \"apackage\": {\n" +
        "              \"id\": \"java.lang\",\n" +
        "              \"name\": \"lang\",\n" +
        "              \"subPackage\": null\n" +
        "            }\n" +
        "          }\n" +
        "        }\n" +
        "      ],\n" +
        "      \"parentClass\": {\n" +
        "        \"emptyHull\": true,\n" +
        "        \"completePath\": \"java.lang.Object\",\n" +
        "        \"className\": \"Object\",\n" +
        "        \"attributes\": [],\n" +
        "        \"parentClass\": null,\n" +
        "        \"interfaces\": [],\n" +
        "        \"apackage\": {\n" +
        "          \"id\": \"java.lang\",\n" +
        "          \"name\": \"lang\",\n" +
        "          \"subPackage\": null\n" +
        "        }\n" +
        "      },\n" +
        "      \"interfaces\": [],\n" +
        "      \"apackage\": {\n" +
        "        \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "        \"name\": \"exampleData\",\n" +
        "        \"subPackage\": null\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"emptyHull\": true,\n" +
        "      \"completePath\": \"java.lang.Integer\",\n" +
        "      \"className\": \"Integer\",\n" +
        "      \"attributes\": [],\n" +
        "      \"parentClass\": null,\n" +
        "      \"interfaces\": [],\n" +
        "      \"apackage\": {\n" +
        "        \"id\": \"java.lang\",\n" +
        "        \"name\": \"lang\",\n" +
        "        \"subPackage\": null\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"emptyHull\": true,\n" +
        "      \"completePath\": \"java.lang.Boolean\",\n" +
        "      \"className\": \"Boolean\",\n" +
        "      \"attributes\": [],\n" +
        "      \"parentClass\": null,\n" +
        "      \"interfaces\": [],\n" +
        "      \"apackage\": {\n" +
        "        \"id\": \"java.lang\",\n" +
        "        \"name\": \"lang\",\n" +
        "        \"subPackage\": null\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"emptyHull\": true,\n" +
        "      \"completePath\": \"java.lang.Object\",\n" +
        "      \"className\": \"Object\",\n" +
        "      \"attributes\": [],\n" +
        "      \"parentClass\": null,\n" +
        "      \"interfaces\": [],\n" +
        "      \"apackage\": {\n" +
        "        \"id\": \"java.lang\",\n" +
        "        \"name\": \"lang\",\n" +
        "        \"subPackage\": null\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"emptyHull\": false,\n" +
        "      \"completePath\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithClasses\",\n" +
        "      \"className\": \"ClassWithClasses\",\n" +
        "      \"attributes\": [\n" +
        "        {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithClassesclassWithPrimtives\",\n" +
        "          \"name\": \"classWithPrimtives\",\n" +
        "          \"accessModifier\": \"private\",\n" +
        "          \"genericType\": null,\n" +
        "          \"clazz\": {\n" +
        "            \"emptyHull\": false,\n" +
        "            \"completePath\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtives\",\n" +
        "            \"className\": \"ClassWithPrimtives\",\n" +
        "            \"attributes\": [\n" +
        "              {\n" +
        "                \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesis\",\n" +
        "                \"name\": \"is\",\n" +
        "                \"accessModifier\": \"\",\n" +
        "                \"genericType\": null,\n" +
        "                \"clazz\": {\n" +
        "                  \"emptyHull\": true,\n" +
        "                  \"completePath\": \"java.lang.Boolean\",\n" +
        "                  \"className\": \"Boolean\",\n" +
        "                  \"attributes\": [],\n" +
        "                  \"parentClass\": null,\n" +
        "                  \"interfaces\": [],\n" +
        "                  \"apackage\": {\n" +
        "                    \"id\": \"java.lang\",\n" +
        "                    \"name\": \"lang\",\n" +
        "                    \"subPackage\": null\n" +
        "                  }\n" +
        "                }\n" +
        "              },\n" +
        "              {\n" +
        "                \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesid\",\n" +
        "                \"name\": \"id\",\n" +
        "                \"accessModifier\": \"\",\n" +
        "                \"genericType\": null,\n" +
        "                \"clazz\": {\n" +
        "                  \"emptyHull\": true,\n" +
        "                  \"completePath\": \"java.lang.Integer\",\n" +
        "                  \"className\": \"Integer\",\n" +
        "                  \"attributes\": [],\n" +
        "                  \"parentClass\": null,\n" +
        "                  \"interfaces\": [],\n" +
        "                  \"apackage\": {\n" +
        "                    \"id\": \"java.lang\",\n" +
        "                    \"name\": \"lang\",\n" +
        "                    \"subPackage\": null\n" +
        "                  }\n" +
        "                }\n" +
        "              }\n" +
        "            ],\n" +
        "            \"parentClass\": {\n" +
        "              \"emptyHull\": true,\n" +
        "              \"completePath\": \"java.lang.Object\",\n" +
        "              \"className\": \"Object\",\n" +
        "              \"attributes\": [],\n" +
        "              \"parentClass\": null,\n" +
        "              \"interfaces\": [],\n" +
        "              \"apackage\": {\n" +
        "                \"id\": \"java.lang\",\n" +
        "                \"name\": \"lang\",\n" +
        "                \"subPackage\": null\n" +
        "              }\n" +
        "            },\n" +
        "            \"interfaces\": [],\n" +
        "            \"apackage\": {\n" +
        "              \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "              \"name\": \"exampleData\",\n" +
        "              \"subPackage\": null\n" +
        "            }\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithClassesanotherClassWithPrimitives\",\n" +
        "          \"name\": \"anotherClassWithPrimitives\",\n" +
        "          \"accessModifier\": \"private\",\n" +
        "          \"genericType\": null,\n" +
        "          \"clazz\": {\n" +
        "            \"emptyHull\": false,\n" +
        "            \"completePath\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitives\",\n" +
        "            \"className\": \"AnotherClassWithPrimitives\",\n" +
        "            \"attributes\": [\n" +
        "              {\n" +
        "                \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitivesid\",\n" +
        "                \"name\": \"id\",\n" +
        "                \"accessModifier\": \"\",\n" +
        "                \"genericType\": null,\n" +
        "                \"clazz\": {\n" +
        "                  \"emptyHull\": true,\n" +
        "                  \"completePath\": \"java.lang.Integer\",\n" +
        "                  \"className\": \"Integer\",\n" +
        "                  \"attributes\": [],\n" +
        "                  \"parentClass\": null,\n" +
        "                  \"interfaces\": [],\n" +
        "                  \"apackage\": {\n" +
        "                    \"id\": \"java.lang\",\n" +
        "                    \"name\": \"lang\",\n" +
        "                    \"subPackage\": null\n" +
        "                  }\n" +
        "                }\n" +
        "              },\n" +
        "              {\n" +
        "                \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitivesis\",\n" +
        "                \"name\": \"is\",\n" +
        "                \"accessModifier\": \"\",\n" +
        "                \"genericType\": null,\n" +
        "                \"clazz\": {\n" +
        "                  \"emptyHull\": true,\n" +
        "                  \"completePath\": \"java.lang.Boolean\",\n" +
        "                  \"className\": \"Boolean\",\n" +
        "                  \"attributes\": [],\n" +
        "                  \"parentClass\": null,\n" +
        "                  \"interfaces\": [],\n" +
        "                  \"apackage\": {\n" +
        "                    \"id\": \"java.lang\",\n" +
        "                    \"name\": \"lang\",\n" +
        "                    \"subPackage\": null\n" +
        "                  }\n" +
        "                }\n" +
        "              }\n" +
        "            ],\n" +
        "            \"parentClass\": {\n" +
        "              \"emptyHull\": true,\n" +
        "              \"completePath\": \"java.lang.Object\",\n" +
        "              \"className\": \"Object\",\n" +
        "              \"attributes\": [],\n" +
        "              \"parentClass\": null,\n" +
        "              \"interfaces\": [],\n" +
        "              \"apackage\": {\n" +
        "                \"id\": \"java.lang\",\n" +
        "                \"name\": \"lang\",\n" +
        "                \"subPackage\": null\n" +
        "              }\n" +
        "            },\n" +
        "            \"interfaces\": [],\n" +
        "            \"apackage\": {\n" +
        "              \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "              \"name\": \"exampleData\",\n" +
        "              \"subPackage\": null\n" +
        "            }\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithClassesdefaultClass\",\n" +
        "          \"name\": \"defaultClass\",\n" +
        "          \"accessModifier\": \"private\",\n" +
        "          \"genericType\": null,\n" +
        "          \"clazz\": {\n" +
        "            \"emptyHull\": true,\n" +
        "            \"completePath\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass\",\n" +
        "            \"className\": \"DefaultClass\",\n" +
        "            \"attributes\": [],\n" +
        "            \"parentClass\": null,\n" +
        "            \"interfaces\": [],\n" +
        "            \"apackage\": {\n" +
        "              \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "              \"name\": \"exampleData\",\n" +
        "              \"subPackage\": null\n" +
        "            }\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithClassesclassWithPrimtivesAndAccessModiers\",\n" +
        "          \"name\": \"classWithPrimtivesAndAccessModiers\",\n" +
        "          \"accessModifier\": \"private\",\n" +
        "          \"genericType\": null,\n" +
        "          \"clazz\": {\n" +
        "            \"emptyHull\": false,\n" +
        "            \"completePath\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiers\",\n" +
        "            \"className\": \"ClassWithPrimtivesAndAccessModiers\",\n" +
        "            \"attributes\": [\n" +
        "              {\n" +
        "                \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModierstest\",\n" +
        "                \"name\": \"test\",\n" +
        "                \"accessModifier\": \"private\",\n" +
        "                \"genericType\": null,\n" +
        "                \"clazz\": {\n" +
        "                  \"emptyHull\": true,\n" +
        "                  \"completePath\": \"java.lang.Integer\",\n" +
        "                  \"className\": \"Integer\",\n" +
        "                  \"attributes\": [],\n" +
        "                  \"parentClass\": null,\n" +
        "                  \"interfaces\": [],\n" +
        "                  \"apackage\": {\n" +
        "                    \"id\": \"java.lang\",\n" +
        "                    \"name\": \"lang\",\n" +
        "                    \"subPackage\": null\n" +
        "                  }\n" +
        "                }\n" +
        "              },\n" +
        "              {\n" +
        "                \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiersnum\",\n" +
        "                \"name\": \"num\",\n" +
        "                \"accessModifier\": \"public\",\n" +
        "                \"genericType\": null,\n" +
        "                \"clazz\": {\n" +
        "                  \"emptyHull\": true,\n" +
        "                  \"completePath\": \"java.lang.Long\",\n" +
        "                  \"className\": \"Long\",\n" +
        "                  \"attributes\": [],\n" +
        "                  \"parentClass\": null,\n" +
        "                  \"interfaces\": [],\n" +
        "                  \"apackage\": {\n" +
        "                    \"id\": \"java.lang\",\n" +
        "                    \"name\": \"lang\",\n" +
        "                    \"subPackage\": null\n" +
        "                  }\n" +
        "                }\n" +
        "              },\n" +
        "              {\n" +
        "                \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiersis\",\n" +
        "                \"name\": \"is\",\n" +
        "                \"accessModifier\": \"private static\",\n" +
        "                \"genericType\": null,\n" +
        "                \"clazz\": {\n" +
        "                  \"emptyHull\": true,\n" +
        "                  \"completePath\": \"java.lang.Boolean\",\n" +
        "                  \"className\": \"Boolean\",\n" +
        "                  \"attributes\": [],\n" +
        "                  \"parentClass\": null,\n" +
        "                  \"interfaces\": [],\n" +
        "                  \"apackage\": {\n" +
        "                    \"id\": \"java.lang\",\n" +
        "                    \"name\": \"lang\",\n" +
        "                    \"subPackage\": null\n" +
        "                  }\n" +
        "                }\n" +
        "              },\n" +
        "              {\n" +
        "                \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiersid\",\n" +
        "                \"name\": \"id\",\n" +
        "                \"accessModifier\": \"public static\",\n" +
        "                \"genericType\": null,\n" +
        "                \"clazz\": {\n" +
        "                  \"emptyHull\": true,\n" +
        "                  \"completePath\": \"java.lang.Integer\",\n" +
        "                  \"className\": \"Integer\",\n" +
        "                  \"attributes\": [],\n" +
        "                  \"parentClass\": null,\n" +
        "                  \"interfaces\": [],\n" +
        "                  \"apackage\": {\n" +
        "                    \"id\": \"java.lang\",\n" +
        "                    \"name\": \"lang\",\n" +
        "                    \"subPackage\": null\n" +
        "                  }\n" +
        "                }\n" +
        "              }\n" +
        "            ],\n" +
        "            \"parentClass\": {\n" +
        "              \"emptyHull\": true,\n" +
        "              \"completePath\": \"java.lang.Object\",\n" +
        "              \"className\": \"Object\",\n" +
        "              \"attributes\": [],\n" +
        "              \"parentClass\": null,\n" +
        "              \"interfaces\": [],\n" +
        "              \"apackage\": {\n" +
        "                \"id\": \"java.lang\",\n" +
        "                \"name\": \"lang\",\n" +
        "                \"subPackage\": null\n" +
        "              }\n" +
        "            },\n" +
        "            \"interfaces\": [],\n" +
        "            \"apackage\": {\n" +
        "              \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "              \"name\": \"exampleData\",\n" +
        "              \"subPackage\": null\n" +
        "            }\n" +
        "          }\n" +
        "        }\n" +
        "      ],\n" +
        "      \"parentClass\": {\n" +
        "        \"emptyHull\": true,\n" +
        "        \"completePath\": \"java.lang.Object\",\n" +
        "        \"className\": \"Object\",\n" +
        "        \"attributes\": [],\n" +
        "        \"parentClass\": null,\n" +
        "        \"interfaces\": [],\n" +
        "        \"apackage\": {\n" +
        "          \"id\": \"java.lang\",\n" +
        "          \"name\": \"lang\",\n" +
        "          \"subPackage\": null\n" +
        "        }\n" +
        "      },\n" +
        "      \"interfaces\": [],\n" +
        "      \"apackage\": {\n" +
        "        \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "        \"name\": \"exampleData\",\n" +
        "        \"subPackage\": null\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"emptyHull\": true,\n" +
        "      \"completePath\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass\",\n" +
        "      \"className\": \"DefaultClass\",\n" +
        "      \"attributes\": [],\n" +
        "      \"parentClass\": null,\n" +
        "      \"interfaces\": [],\n" +
        "      \"apackage\": {\n" +
        "        \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "        \"name\": \"exampleData\",\n" +
        "        \"subPackage\": null\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"emptyHull\": false,\n" +
        "      \"completePath\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtives\",\n" +
        "      \"className\": \"ClassWithPrimtives\",\n" +
        "      \"attributes\": [\n" +
        "        {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesis\",\n" +
        "          \"name\": \"is\",\n" +
        "          \"accessModifier\": \"\",\n" +
        "          \"genericType\": null,\n" +
        "          \"clazz\": {\n" +
        "            \"emptyHull\": true,\n" +
        "            \"completePath\": \"java.lang.Boolean\",\n" +
        "            \"className\": \"Boolean\",\n" +
        "            \"attributes\": [],\n" +
        "            \"parentClass\": null,\n" +
        "            \"interfaces\": [],\n" +
        "            \"apackage\": {\n" +
        "              \"id\": \"java.lang\",\n" +
        "              \"name\": \"lang\",\n" +
        "              \"subPackage\": null\n" +
        "            }\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesid\",\n" +
        "          \"name\": \"id\",\n" +
        "          \"accessModifier\": \"\",\n" +
        "          \"genericType\": null,\n" +
        "          \"clazz\": {\n" +
        "            \"emptyHull\": true,\n" +
        "            \"completePath\": \"java.lang.Integer\",\n" +
        "            \"className\": \"Integer\",\n" +
        "            \"attributes\": [],\n" +
        "            \"parentClass\": null,\n" +
        "            \"interfaces\": [],\n" +
        "            \"apackage\": {\n" +
        "              \"id\": \"java.lang\",\n" +
        "              \"name\": \"lang\",\n" +
        "              \"subPackage\": null\n" +
        "            }\n" +
        "          }\n" +
        "        }\n" +
        "      ],\n" +
        "      \"parentClass\": {\n" +
        "        \"emptyHull\": true,\n" +
        "        \"completePath\": \"java.lang.Object\",\n" +
        "        \"className\": \"Object\",\n" +
        "        \"attributes\": [],\n" +
        "        \"parentClass\": null,\n" +
        "        \"interfaces\": [],\n" +
        "        \"apackage\": {\n" +
        "          \"id\": \"java.lang\",\n" +
        "          \"name\": \"lang\",\n" +
        "          \"subPackage\": null\n" +
        "        }\n" +
        "      },\n" +
        "      \"interfaces\": [],\n" +
        "      \"apackage\": {\n" +
        "        \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "        \"name\": \"exampleData\",\n" +
        "        \"subPackage\": null\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"emptyHull\": false,\n" +
        "      \"completePath\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiers\",\n" +
        "      \"className\": \"ClassWithPrimtivesAndAccessModiers\",\n" +
        "      \"attributes\": [\n" +
        "        {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModierstest\",\n" +
        "          \"name\": \"test\",\n" +
        "          \"accessModifier\": \"private\",\n" +
        "          \"genericType\": null,\n" +
        "          \"clazz\": {\n" +
        "            \"emptyHull\": true,\n" +
        "            \"completePath\": \"java.lang.Integer\",\n" +
        "            \"className\": \"Integer\",\n" +
        "            \"attributes\": [],\n" +
        "            \"parentClass\": null,\n" +
        "            \"interfaces\": [],\n" +
        "            \"apackage\": {\n" +
        "              \"id\": \"java.lang\",\n" +
        "              \"name\": \"lang\",\n" +
        "              \"subPackage\": null\n" +
        "            }\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiersnum\",\n" +
        "          \"name\": \"num\",\n" +
        "          \"accessModifier\": \"public\",\n" +
        "          \"genericType\": null,\n" +
        "          \"clazz\": {\n" +
        "            \"emptyHull\": true,\n" +
        "            \"completePath\": \"java.lang.Long\",\n" +
        "            \"className\": \"Long\",\n" +
        "            \"attributes\": [],\n" +
        "            \"parentClass\": null,\n" +
        "            \"interfaces\": [],\n" +
        "            \"apackage\": {\n" +
        "              \"id\": \"java.lang\",\n" +
        "              \"name\": \"lang\",\n" +
        "              \"subPackage\": null\n" +
        "            }\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiersis\",\n" +
        "          \"name\": \"is\",\n" +
        "          \"accessModifier\": \"private static\",\n" +
        "          \"genericType\": null,\n" +
        "          \"clazz\": {\n" +
        "            \"emptyHull\": true,\n" +
        "            \"completePath\": \"java.lang.Boolean\",\n" +
        "            \"className\": \"Boolean\",\n" +
        "            \"attributes\": [],\n" +
        "            \"parentClass\": null,\n" +
        "            \"interfaces\": [],\n" +
        "            \"apackage\": {\n" +
        "              \"id\": \"java.lang\",\n" +
        "              \"name\": \"lang\",\n" +
        "              \"subPackage\": null\n" +
        "            }\n" +
        "          }\n" +
        "        },\n" +
        "        {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiersid\",\n" +
        "          \"name\": \"id\",\n" +
        "          \"accessModifier\": \"public static\",\n" +
        "          \"genericType\": null,\n" +
        "          \"clazz\": {\n" +
        "            \"emptyHull\": true,\n" +
        "            \"completePath\": \"java.lang.Integer\",\n" +
        "            \"className\": \"Integer\",\n" +
        "            \"attributes\": [],\n" +
        "            \"parentClass\": null,\n" +
        "            \"interfaces\": [],\n" +
        "            \"apackage\": {\n" +
        "              \"id\": \"java.lang\",\n" +
        "              \"name\": \"lang\",\n" +
        "              \"subPackage\": null\n" +
        "            }\n" +
        "          }\n" +
        "        }\n" +
        "      ],\n" +
        "      \"parentClass\": {\n" +
        "        \"emptyHull\": true,\n" +
        "        \"completePath\": \"java.lang.Object\",\n" +
        "        \"className\": \"Object\",\n" +
        "        \"attributes\": [],\n" +
        "        \"parentClass\": null,\n" +
        "        \"interfaces\": [],\n" +
        "        \"apackage\": {\n" +
        "          \"id\": \"java.lang\",\n" +
        "          \"name\": \"lang\",\n" +
        "          \"subPackage\": null\n" +
        "        }\n" +
        "      },\n" +
        "      \"interfaces\": [],\n" +
        "      \"apackage\": {\n" +
        "        \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "        \"name\": \"exampleData\",\n" +
        "        \"subPackage\": null\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"emptyHull\": true,\n" +
        "      \"completePath\": \"java.lang.Long\",\n" +
        "      \"className\": \"Long\",\n" +
        "      \"attributes\": [],\n" +
        "      \"parentClass\": null,\n" +
        "      \"interfaces\": [],\n" +
        "      \"apackage\": {\n" +
        "        \"id\": \"java.lang\",\n" +
        "        \"name\": \"lang\",\n" +
        "        \"subPackage\": null\n" +
        "      }\n" +
        "    }\n" +
        "  ],\n" +
        "  \"packageList\": [\n" +
        "    {\n" +
        "      \"id\": \"java\",\n" +
        "      \"name\": \"java\",\n" +
        "      \"subPackage\": {\n" +
        "        \"id\": \"java.lang\",\n" +
        "        \"name\": \"lang\",\n" +
        "        \"subPackage\": null\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": \"java.lang\",\n" +
        "      \"name\": \"lang\",\n" +
        "      \"subPackage\": null\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": \"de\",\n" +
        "      \"name\": \"de\",\n" +
        "      \"subPackage\": {\n" +
        "        \"id\": \"de.fh\",\n" +
        "        \"name\": \"fh\",\n" +
        "        \"subPackage\": {\n" +
        "          \"id\": \"de.fh.kiel\",\n" +
        "          \"name\": \"kiel\",\n" +
        "          \"subPackage\": {\n" +
        "            \"id\": \"de.fh.kiel.advancedjava\",\n" +
        "            \"name\": \"advancedjava\",\n" +
        "            \"subPackage\": {\n" +
        "              \"id\": \"de.fh.kiel.advancedjava.pojomodel\",\n" +
        "              \"name\": \"pojomodel\",\n" +
        "              \"subPackage\": {\n" +
        "                \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "                \"name\": \"exampleData\",\n" +
        "                \"subPackage\": null\n" +
        "              }\n" +
        "            }\n" +
        "          }\n" +
        "        }\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": \"de.fh\",\n" +
        "      \"name\": \"fh\",\n" +
        "      \"subPackage\": {\n" +
        "        \"id\": \"de.fh.kiel\",\n" +
        "        \"name\": \"kiel\",\n" +
        "        \"subPackage\": {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava\",\n" +
        "          \"name\": \"advancedjava\",\n" +
        "          \"subPackage\": {\n" +
        "            \"id\": \"de.fh.kiel.advancedjava.pojomodel\",\n" +
        "            \"name\": \"pojomodel\",\n" +
        "            \"subPackage\": {\n" +
        "              \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "              \"name\": \"exampleData\",\n" +
        "              \"subPackage\": null\n" +
        "            }\n" +
        "          }\n" +
        "        }\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": \"de.fh.kiel\",\n" +
        "      \"name\": \"kiel\",\n" +
        "      \"subPackage\": {\n" +
        "        \"id\": \"de.fh.kiel.advancedjava\",\n" +
        "        \"name\": \"advancedjava\",\n" +
        "        \"subPackage\": {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava.pojomodel\",\n" +
        "          \"name\": \"pojomodel\",\n" +
        "          \"subPackage\": {\n" +
        "            \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "            \"name\": \"exampleData\",\n" +
        "            \"subPackage\": null\n" +
        "          }\n" +
        "        }\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": \"de.fh.kiel.advancedjava\",\n" +
        "      \"name\": \"advancedjava\",\n" +
        "      \"subPackage\": {\n" +
        "        \"id\": \"de.fh.kiel.advancedjava.pojomodel\",\n" +
        "        \"name\": \"pojomodel\",\n" +
        "        \"subPackage\": {\n" +
        "          \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "          \"name\": \"exampleData\",\n" +
        "          \"subPackage\": null\n" +
        "        }\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": \"de.fh.kiel.advancedjava.pojomodel\",\n" +
        "      \"name\": \"pojomodel\",\n" +
        "      \"subPackage\": {\n" +
        "        \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "        \"name\": \"exampleData\",\n" +
        "        \"subPackage\": null\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": \"de.fh.kiel.advancedjava.pojomodel.exampleData\",\n" +
        "      \"name\": \"exampleData\",\n" +
        "      \"subPackage\": null\n" +
        "    }\n" +
        "  ]\n" +
        "}")
public class ExportDTO {
    List<Pojo> pojoList;
    List<Package> packageList;
}
