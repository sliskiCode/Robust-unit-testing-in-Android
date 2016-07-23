package com.code.sliski.api

class StackoverflowClient(api: StackoverflowApi) :
        Client,
        StackoverflowApi by api