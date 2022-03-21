import com.apollographql.apollo3.ApolloClient

val appolloClient = ApolloClient.Builder()
    .serverUrl("https://demotivation-quotes-api.herokuapp.com/graphql")
    .build()