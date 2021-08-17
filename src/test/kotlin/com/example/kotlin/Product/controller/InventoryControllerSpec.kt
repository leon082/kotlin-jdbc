package com.example.kotlin.Product.controller

import com.example.kotlin.Product.controller.v1.InventoryController
import com.example.kotlin.Product.service.impl.InventoryService
import com.example.kotlin.Product.utils.BuildObject
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import io.kotest.core.spec.style.FunSpec
import io.mockk.every
import org.springframework.http.MediaType
import io.mockk.mockk
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class InventoryControllerSpec : FunSpec() {

    init {
        val buildObject = BuildObject()

        val service = mockk<InventoryService>()

        val objectMapper = Jackson2ObjectMapperBuilder()
            .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE).build<ObjectMapper>()


        lateinit var mockMvc: MockMvc

        beforeTest {
            mockMvc = MockMvcBuilders.standaloneSetup(InventoryController(service))
                .setMessageConverters()
                .setMessageConverters(MappingJackson2HttpMessageConverter(objectMapper))
                .build()
        }

        test("Get Inventory successful") {
            every { service.getInventory(any()) } returns buildObject.aValidInventoryDTO()

            mockMvc.perform(
                get("/api/v1/inventory?ownerId=1")
                    .accept(MediaType.APPLICATION_JSON)
            )
                .andExpect(status().isOk)
        }


    }

}