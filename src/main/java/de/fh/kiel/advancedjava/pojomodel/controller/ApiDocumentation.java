package de.fh.kiel.advancedjava.pojomodel.controller;

public class ApiDocumentation {
    public static final String BASE_64_POJO = "yv66vgAAADsAGAoAAgADBwAEDAAFAAYBABBqYXZhL2xhbmcvT2JqZWN0AQAGPGluaXQ+AQADKClWBwAIAQA+ZGUvZmgva2llbC9hZHZhbmNlZGphdmEvcG9qb21vZGVsL2V4YW1wbGVEYXRhL0NsYXNzV2l0aENsYXNzZXMBABphbm90aGVyQ2xhc3NXaXRoUHJpbWl0aXZlcwEASkxkZS9maC9raWVsL2FkdmFuY2VkamF2YS9wb2pvbW9kZWwvZXhhbXBsZURhdGEvQW5vdGhlckNsYXNzV2l0aFByaW1pdGl2ZXM7AQASY2xhc3NXaXRoUHJpbXRpdmVzAQBCTGRlL2ZoL2tpZWwvYWR2YW5jZWRqYXZhL3Bvam9tb2RlbC9leGFtcGxlRGF0YS9DbGFzc1dpdGhQcmltdGl2ZXM7AQAiY2xhc3NXaXRoUHJpbXRpdmVzQW5kQWNjZXNzTW9kaWVycwEAUkxkZS9maC9raWVsL2FkdmFuY2VkamF2YS9wb2pvbW9kZWwvZXhhbXBsZURhdGEvQ2xhc3NXaXRoUHJpbXRpdmVzQW5kQWNjZXNzTW9kaWVyczsBAAxkZWZhdWx0Q2xhc3MBADxMZGUvZmgva2llbC9hZHZhbmNlZGphdmEvcG9qb21vZGVsL2V4YW1wbGVEYXRhL0RlZmF1bHRDbGFzczsBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEAQExkZS9maC9raWVsL2FkdmFuY2VkamF2YS9wb2pvbW9kZWwvZXhhbXBsZURhdGEvQ2xhc3NXaXRoQ2xhc3NlczsBAApTb3VyY2VGaWxlAQAVQ2xhc3NXaXRoQ2xhc3Nlcy5qYXZhACEABwACAAAABAACAAkACgAAAAIACwAMAAAAAgANAA4AAAACAA8AEAAAAAEAAQAFAAYAAQARAAAALwABAAEAAAAFKrcAAbEAAAACABIAAAAGAAEAAAADABMAAAAMAAEAAAAFABQAFQAAAAEAFgAAAAIAFw==";
    public static final String BASE_64_JAR = "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";
    public static final String ADD_ATTRIBUTE_DTO = """
            {
              "name": "something",
              "type": "java.util.List",
              "visibility": "private",
              "genericType": "int"
            }
            """;
    public static final String ATTRIBUTE_DELETE_DTO = """
            {
              "className": "de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass",
              "packageName": "de.fh.kiel.advancedjava.pojomodel.exampleData",
              "attributeName": "name"
            }
            """;
    public static final String EXPORT_DTO = """
            {
              "pojoList": [
                {
                  "emptyHull": false,
                  "completePath": "de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitives",
                  "className": "AnotherClassWithPrimitives",
                  "attributes": [
                    {
                      "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitivesid",
                      "name": "id",
                      "accessModifier": "",
                      "genericType": null,
                      "clazz": {
                        "emptyHull": true,
                        "completePath": "java.lang.Integer",
                        "className": "Integer",
                        "attributes": [],
                        "parentClass": null,
                        "interfaces": [],
                        "apackage": {
                          "id": "java.lang",
                          "name": "lang",
                          "subPackage": null
                        }
                      }
                    },
                    {
                      "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitivesis",
                      "name": "is",
                      "accessModifier": "",
                      "genericType": null,
                      "clazz": {
                        "emptyHull": true,
                        "completePath": "java.lang.Boolean",
                        "className": "Boolean",
                        "attributes": [],
                        "parentClass": null,
                        "interfaces": [],
                        "apackage": {
                          "id": "java.lang",
                          "name": "lang",
                          "subPackage": null
                        }
                      }
                    }
                  ],
                  "parentClass": {
                    "emptyHull": true,
                    "completePath": "java.lang.Object",
                    "className": "Object",
                    "attributes": [],
                    "parentClass": null,
                    "interfaces": [],
                    "apackage": {
                      "id": "java.lang",
                      "name": "lang",
                      "subPackage": null
                    }
                  },
                  "interfaces": [],
                  "apackage": {
                    "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                    "name": "exampleData",
                    "subPackage": null
                  }
                },
                {
                  "emptyHull": true,
                  "completePath": "java.lang.Integer",
                  "className": "Integer",
                  "attributes": [],
                  "parentClass": null,
                  "interfaces": [],
                  "apackage": {
                    "id": "java.lang",
                    "name": "lang",
                    "subPackage": null
                  }
                },
                {
                  "emptyHull": true,
                  "completePath": "java.lang.Boolean",
                  "className": "Boolean",
                  "attributes": [],
                  "parentClass": null,
                  "interfaces": [],
                  "apackage": {
                    "id": "java.lang",
                    "name": "lang",
                    "subPackage": null
                  }
                },
                {
                  "emptyHull": true,
                  "completePath": "java.lang.Object",
                  "className": "Object",
                  "attributes": [],
                  "parentClass": null,
                  "interfaces": [],
                  "apackage": {
                    "id": "java.lang",
                    "name": "lang",
                    "subPackage": null
                  }
                },
                {
                  "emptyHull": false,
                  "completePath": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithClasses",
                  "className": "ClassWithClasses",
                  "attributes": [
                    {
                      "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithClassesclassWithPrimtives",
                      "name": "classWithPrimtives",
                      "accessModifier": "private",
                      "genericType": null,
                      "clazz": {
                        "emptyHull": false,
                        "completePath": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtives",
                        "className": "ClassWithPrimtives",
                        "attributes": [
                          {
                            "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesis",
                            "name": "is",
                            "accessModifier": "",
                            "genericType": null,
                            "clazz": {
                              "emptyHull": true,
                              "completePath": "java.lang.Boolean",
                              "className": "Boolean",
                              "attributes": [],
                              "parentClass": null,
                              "interfaces": [],
                              "apackage": {
                                "id": "java.lang",
                                "name": "lang",
                                "subPackage": null
                              }
                            }
                          },
                          {
                            "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesid",
                            "name": "id",
                            "accessModifier": "",
                            "genericType": null,
                            "clazz": {
                              "emptyHull": true,
                              "completePath": "java.lang.Integer",
                              "className": "Integer",
                              "attributes": [],
                              "parentClass": null,
                              "interfaces": [],
                              "apackage": {
                                "id": "java.lang",
                                "name": "lang",
                                "subPackage": null
                              }
                            }
                          }
                        ],
                        "parentClass": {
                          "emptyHull": true,
                          "completePath": "java.lang.Object",
                          "className": "Object",
                          "attributes": [],
                          "parentClass": null,
                          "interfaces": [],
                          "apackage": {
                            "id": "java.lang",
                            "name": "lang",
                            "subPackage": null
                          }
                        },
                        "interfaces": [],
                        "apackage": {
                          "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                          "name": "exampleData",
                          "subPackage": null
                        }
                      }
                    },
                    {
                      "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithClassesanotherClassWithPrimitives",
                      "name": "anotherClassWithPrimitives",
                      "accessModifier": "private",
                      "genericType": null,
                      "clazz": {
                        "emptyHull": false,
                        "completePath": "de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitives",
                        "className": "AnotherClassWithPrimitives",
                        "attributes": [
                          {
                            "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitivesid",
                            "name": "id",
                            "accessModifier": "",
                            "genericType": null,
                            "clazz": {
                              "emptyHull": true,
                              "completePath": "java.lang.Integer",
                              "className": "Integer",
                              "attributes": [],
                              "parentClass": null,
                              "interfaces": [],
                              "apackage": {
                                "id": "java.lang",
                                "name": "lang",
                                "subPackage": null
                              }
                            }
                          },
                          {
                            "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitivesis",
                            "name": "is",
                            "accessModifier": "",
                            "genericType": null,
                            "clazz": {
                              "emptyHull": true,
                              "completePath": "java.lang.Boolean",
                              "className": "Boolean",
                              "attributes": [],
                              "parentClass": null,
                              "interfaces": [],
                              "apackage": {
                                "id": "java.lang",
                                "name": "lang",
                                "subPackage": null
                              }
                            }
                          }
                        ],
                        "parentClass": {
                          "emptyHull": true,
                          "completePath": "java.lang.Object",
                          "className": "Object",
                          "attributes": [],
                          "parentClass": null,
                          "interfaces": [],
                          "apackage": {
                            "id": "java.lang",
                            "name": "lang",
                            "subPackage": null
                          }
                        },
                        "interfaces": [],
                        "apackage": {
                          "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                          "name": "exampleData",
                          "subPackage": null
                        }
                      }
                    },
                    {
                      "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithClassesdefaultClass",
                      "name": "defaultClass",
                      "accessModifier": "private",
                      "genericType": null,
                      "clazz": {
                        "emptyHull": true,
                        "completePath": "de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass",
                        "className": "DefaultClass",
                        "attributes": [],
                        "parentClass": null,
                        "interfaces": [],
                        "apackage": {
                          "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                          "name": "exampleData",
                          "subPackage": null
                        }
                      }
                    },
                    {
                      "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithClassesclassWithPrimtivesAndAccessModiers",
                      "name": "classWithPrimtivesAndAccessModiers",
                      "accessModifier": "private",
                      "genericType": null,
                      "clazz": {
                        "emptyHull": false,
                        "completePath": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiers",
                        "className": "ClassWithPrimtivesAndAccessModiers",
                        "attributes": [
                          {
                            "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModierstest",
                            "name": "test",
                            "accessModifier": "private",
                            "genericType": null,
                            "clazz": {
                              "emptyHull": true,
                              "completePath": "java.lang.Integer",
                              "className": "Integer",
                              "attributes": [],
                              "parentClass": null,
                              "interfaces": [],
                              "apackage": {
                                "id": "java.lang",
                                "name": "lang",
                                "subPackage": null
                              }
                            }
                          },
                          {
                            "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiersnum",
                            "name": "num",
                            "accessModifier": "public",
                            "genericType": null,
                            "clazz": {
                              "emptyHull": true,
                              "completePath": "java.lang.Long",
                              "className": "Long",
                              "attributes": [],
                              "parentClass": null,
                              "interfaces": [],
                              "apackage": {
                                "id": "java.lang",
                                "name": "lang",
                                "subPackage": null
                              }
                            }
                          },
                          {
                            "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiersis",
                            "name": "is",
                            "accessModifier": "private static",
                            "genericType": null,
                            "clazz": {
                              "emptyHull": true,
                              "completePath": "java.lang.Boolean",
                              "className": "Boolean",
                              "attributes": [],
                              "parentClass": null,
                              "interfaces": [],
                              "apackage": {
                                "id": "java.lang",
                                "name": "lang",
                                "subPackage": null
                              }
                            }
                          },
                          {
                            "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiersid",
                            "name": "id",
                            "accessModifier": "public static",
                            "genericType": null,
                            "clazz": {
                              "emptyHull": true,
                              "completePath": "java.lang.Integer",
                              "className": "Integer",
                              "attributes": [],
                              "parentClass": null,
                              "interfaces": [],
                              "apackage": {
                                "id": "java.lang",
                                "name": "lang",
                                "subPackage": null
                              }
                            }
                          }
                        ],
                        "parentClass": {
                          "emptyHull": true,
                          "completePath": "java.lang.Object",
                          "className": "Object",
                          "attributes": [],
                          "parentClass": null,
                          "interfaces": [],
                          "apackage": {
                            "id": "java.lang",
                            "name": "lang",
                            "subPackage": null
                          }
                        },
                        "interfaces": [],
                        "apackage": {
                          "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                          "name": "exampleData",
                          "subPackage": null
                        }
                      }
                    }
                  ],
                  "parentClass": {
                    "emptyHull": true,
                    "completePath": "java.lang.Object",
                    "className": "Object",
                    "attributes": [],
                    "parentClass": null,
                    "interfaces": [],
                    "apackage": {
                      "id": "java.lang",
                      "name": "lang",
                      "subPackage": null
                    }
                  },
                  "interfaces": [],
                  "apackage": {
                    "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                    "name": "exampleData",
                    "subPackage": null
                  }
                },
                {
                  "emptyHull": true,
                  "completePath": "de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClass",
                  "className": "DefaultClass",
                  "attributes": [],
                  "parentClass": null,
                  "interfaces": [],
                  "apackage": {
                    "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                    "name": "exampleData",
                    "subPackage": null
                  }
                },
                {
                  "emptyHull": false,
                  "completePath": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtives",
                  "className": "ClassWithPrimtives",
                  "attributes": [
                    {
                      "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesis",
                      "name": "is",
                      "accessModifier": "",
                      "genericType": null,
                      "clazz": {
                        "emptyHull": true,
                        "completePath": "java.lang.Boolean",
                        "className": "Boolean",
                        "attributes": [],
                        "parentClass": null,
                        "interfaces": [],
                        "apackage": {
                          "id": "java.lang",
                          "name": "lang",
                          "subPackage": null
                        }
                      }
                    },
                    {
                      "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesid",
                      "name": "id",
                      "accessModifier": "",
                      "genericType": null,
                      "clazz": {
                        "emptyHull": true,
                        "completePath": "java.lang.Integer",
                        "className": "Integer",
                        "attributes": [],
                        "parentClass": null,
                        "interfaces": [],
                        "apackage": {
                          "id": "java.lang",
                          "name": "lang",
                          "subPackage": null
                        }
                      }
                    }
                  ],
                  "parentClass": {
                    "emptyHull": true,
                    "completePath": "java.lang.Object",
                    "className": "Object",
                    "attributes": [],
                    "parentClass": null,
                    "interfaces": [],
                    "apackage": {
                      "id": "java.lang",
                      "name": "lang",
                      "subPackage": null
                    }
                  },
                  "interfaces": [],
                  "apackage": {
                    "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                    "name": "exampleData",
                    "subPackage": null
                  }
                },
                {
                  "emptyHull": false,
                  "completePath": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiers",
                  "className": "ClassWithPrimtivesAndAccessModiers",
                  "attributes": [
                    {
                      "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModierstest",
                      "name": "test",
                      "accessModifier": "private",
                      "genericType": null,
                      "clazz": {
                        "emptyHull": true,
                        "completePath": "java.lang.Integer",
                        "className": "Integer",
                        "attributes": [],
                        "parentClass": null,
                        "interfaces": [],
                        "apackage": {
                          "id": "java.lang",
                          "name": "lang",
                          "subPackage": null
                        }
                      }
                    },
                    {
                      "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiersnum",
                      "name": "num",
                      "accessModifier": "public",
                      "genericType": null,
                      "clazz": {
                        "emptyHull": true,
                        "completePath": "java.lang.Long",
                        "className": "Long",
                        "attributes": [],
                        "parentClass": null,
                        "interfaces": [],
                        "apackage": {
                          "id": "java.lang",
                          "name": "lang",
                          "subPackage": null
                        }
                      }
                    },
                    {
                      "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiersis",
                      "name": "is",
                      "accessModifier": "private static",
                      "genericType": null,
                      "clazz": {
                        "emptyHull": true,
                        "completePath": "java.lang.Boolean",
                        "className": "Boolean",
                        "attributes": [],
                        "parentClass": null,
                        "interfaces": [],
                        "apackage": {
                          "id": "java.lang",
                          "name": "lang",
                          "subPackage": null
                        }
                      }
                    },
                    {
                      "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.ClassWithPrimtivesAndAccessModiersid",
                      "name": "id",
                      "accessModifier": "public static",
                      "genericType": null,
                      "clazz": {
                        "emptyHull": true,
                        "completePath": "java.lang.Integer",
                        "className": "Integer",
                        "attributes": [],
                        "parentClass": null,
                        "interfaces": [],
                        "apackage": {
                          "id": "java.lang",
                          "name": "lang",
                          "subPackage": null
                        }
                      }
                    }
                  ],
                  "parentClass": {
                    "emptyHull": true,
                    "completePath": "java.lang.Object",
                    "className": "Object",
                    "attributes": [],
                    "parentClass": null,
                    "interfaces": [],
                    "apackage": {
                      "id": "java.lang",
                      "name": "lang",
                      "subPackage": null
                    }
                  },
                  "interfaces": [],
                  "apackage": {
                    "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                    "name": "exampleData",
                    "subPackage": null
                  }
                },
                {
                  "emptyHull": true,
                  "completePath": "java.lang.Long",
                  "className": "Long",
                  "attributes": [],
                  "parentClass": null,
                  "interfaces": [],
                  "apackage": {
                    "id": "java.lang",
                    "name": "lang",
                    "subPackage": null
                  }
                }
              ],
              "packageList": [
                {
                  "id": "java",
                  "name": "java",
                  "subPackage": {
                    "id": "java.lang",
                    "name": "lang",
                    "subPackage": null
                  }
                },
                {
                  "id": "java.lang",
                  "name": "lang",
                  "subPackage": null
                },
                {
                  "id": "de",
                  "name": "de",
                  "subPackage": {
                    "id": "de.fh",
                    "name": "fh",
                    "subPackage": {
                      "id": "de.fh.kiel",
                      "name": "kiel",
                      "subPackage": {
                        "id": "de.fh.kiel.advancedjava",
                        "name": "advancedjava",
                        "subPackage": {
                          "id": "de.fh.kiel.advancedjava.pojomodel",
                          "name": "pojomodel",
                          "subPackage": {
                            "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                            "name": "exampleData",
                            "subPackage": null
                          }
                        }
                      }
                    }
                  }
                },
                {
                  "id": "de.fh",
                  "name": "fh",
                  "subPackage": {
                    "id": "de.fh.kiel",
                    "name": "kiel",
                    "subPackage": {
                      "id": "de.fh.kiel.advancedjava",
                      "name": "advancedjava",
                      "subPackage": {
                        "id": "de.fh.kiel.advancedjava.pojomodel",
                        "name": "pojomodel",
                        "subPackage": {
                          "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                          "name": "exampleData",
                          "subPackage": null
                        }
                      }
                    }
                  }
                },
                {
                  "id": "de.fh.kiel",
                  "name": "kiel",
                  "subPackage": {
                    "id": "de.fh.kiel.advancedjava",
                    "name": "advancedjava",
                    "subPackage": {
                      "id": "de.fh.kiel.advancedjava.pojomodel",
                      "name": "pojomodel",
                      "subPackage": {
                        "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                        "name": "exampleData",
                        "subPackage": null
                      }
                    }
                  }
                },
                {
                  "id": "de.fh.kiel.advancedjava",
                  "name": "advancedjava",
                  "subPackage": {
                    "id": "de.fh.kiel.advancedjava.pojomodel",
                    "name": "pojomodel",
                    "subPackage": {
                      "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                      "name": "exampleData",
                      "subPackage": null
                    }
                  }
                },
                {
                  "id": "de.fh.kiel.advancedjava.pojomodel",
                  "name": "pojomodel",
                  "subPackage": {
                    "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                    "name": "exampleData",
                    "subPackage": null
                  }
                },
                {
                  "id": "de.fh.kiel.advancedjava.pojomodel.exampleData",
                  "name": "exampleData",
                  "subPackage": null
                }
              ]
            }""";

    public static final String POJO_EMPTY_HULL_DTO = """
            {
              "packageName": "de.fh.test",
              "className": "hello"
            }""";
    public static final String POJO_STATS = """
            {
              "classname": "DefaultClass",
              "packageName": "exampleData",
              "numberOfAttributes": 2,
              "parentClassName": "Object",
              "implementedInterfaces": [],
              "numberOfDirectSubClasses": 0,
              "numberOfAttributesThatHaveTheCorrespondingDataType": 0,
              "numberOfClassesInTheSamePackage": 1,
              "numberOfClassesWithTheSameName": 1
            }""";
    public static final String ATTRRIBUTE = """
            {
              "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.DefaultClassname",
              "name": "name",
              "accessModifier": "private",
              "genericType": null,
              "clazz": {
                "emptyHull": true,
                "completePath": "java.lang.String",
                "className": "String",
                "attributes": [],
                "parentClass": null,
                "interfaces": [],
                "apackage": {
                  "id": "java.lang",
                  "name": "lang",
                  "subPackage": null
                }
              }
            }""";

    public static final String POJO = """
            {
              "emptyHull": false,
              "completePath": "de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitives",
              "className": "AnotherClassWithPrimitives",
              "packageName": "de.fh.kiel.advancedjava.pojomodel.exampleData",
              "attributes": [
                {
                  "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitivesid",
                  "name": "id",
                  "accessModifier": "",
                  "genericType": null,
                  "clazz": {
                    "emptyHull": true,
                    "completePath": "java.lang.Integer",
                    "className": "Integer",
                    "packageName": "java.lang",
                    "attributes": [],
                    "parentClass": null,
                    "interfaces": null
                  }
                },
                {
                  "id": "de.fh.kiel.advancedjava.pojomodel.exampleData.AnotherClassWithPrimitivesis",
                  "name": "is",
                  "accessModifier": "",
                  "genericType": null,
                  "clazz": {
                    "emptyHull": true,
                    "completePath": "java.lang.Boolean",
                    "className": "Boolean",
                    "packageName": "java.lang",
                    "attributes": [],
                    "parentClass": null,
                    "interfaces": null
                  }
                }
              ],
              "parentClass": {
                "emptyHull": true,
                "completePath": "java.lang.Object",
                "className": "Object",
                "packageName": "java.lang",
                "attributes": [],
                "parentClass": null,
                "interfaces": null
              },
              "interfaces": []
            }""";
    public static final String PACKAGE = """
            {
                          "id": "java.lang",
                          "name": "lang",
                          "subPackage": null
                        }""";

    private ApiDocumentation() {
    }
}
