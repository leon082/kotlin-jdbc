package com.example.kotlin.Product.controller

import com.example.kotlin.Product.controller.v1.OwnerController
import com.example.kotlin.Product.service.impl.OwnerService
import com.example.kotlin.Product.utils.BuildObject
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import io.kotest.core.spec.style.FunSpec
import io.mockk.every
import io.mockk.mockk
import org.springframework.http.MediaType
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class OwnerControllerSpec : FunSpec() {

    init {
        val buildObject = BuildObject()

        val service = mockk<OwnerService>()

        val objectMapper = Jackson2ObjectMapperBuilder()
            .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE).build<ObjectMapper>()


        lateinit var mockMvc: MockMvc

        beforeTest {
            mockMvc = MockMvcBuilders.standaloneSetup(OwnerController(service))
                .setMessageConverters()
                .setMessageConverters(MappingJackson2HttpMessageConverter(objectMapper))
                .build()
        }

        test("Save Inventory successful") {
            every { service.save(any()) } returns true

            mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/owner")
                    .content(buildObject.aValidJsonOwnerDTO())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)

            ).andExpect(MockMvcResultMatchers.status().isOk)
        }

        test("Update Inventory successful") {
            every { service.update(any()) } returns buildObject.aValidOwnerDTO()

            mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/owner")
                    .content(buildObject.aValidJsonOwnerDTO())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)

            ).andExpect(MockMvcResultMatchers.status().isOk)
        }


    }

}