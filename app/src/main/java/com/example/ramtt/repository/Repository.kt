package com.example.ramtt.repository

import com.example.ramtt.data.datasource.RemoteDataSource
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 8/01/2023
 */

class Repository @Inject constructor(
   remoteDataSource: RemoteDataSource
) {

   val remote = remoteDataSource
}