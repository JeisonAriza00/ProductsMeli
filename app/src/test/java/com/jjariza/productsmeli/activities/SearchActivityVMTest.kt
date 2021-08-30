package com.jjariza.productsmeli.activities

import com.jjariza.productsmeli.models.SearchData
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock

class SearchActivityVMTest {

    private lateinit var searchActivityVM: SearchActivityVM

    @Mock
    private val searchData: SearchData = mock(SearchData::class.java)

    @Before
    fun setUp() {
        searchActivityVM = SearchActivityVM()
    }

    @Test
    fun `when test`() {
        searchActivityVM.getSearchData("ps5")
        assertNotNull(searchData)
    }
}