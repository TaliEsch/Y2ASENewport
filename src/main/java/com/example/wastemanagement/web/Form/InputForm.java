package com.example.wastemanagement.web.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.h2.expression.function.table.CSVReadFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.System.in;

/* This data class is used to store the data from the input form and to make sure the data is within a set
parameter. */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputForm {
    @NotEmpty
    private String item;

    @NotEmpty
    @Size(min = 1, max = 1000)
    private String opinion;

    private static final Logger log = LoggerFactory.getLogger(InputForm.class);

    @Autowired
    private ResourceLoader resourceLoader;

    private static final char COMMA_DELIMITER = ',';
    private ArrayList<String> bannedWords;




}



