export type BookErrorType = keyof typeof error ;

const error =  {
    bookCode : {
        hasError : false,
        msg : '',
    },

    bookTitle : {
        hasError : false,
        msg : '',
    },

    bookAuthor : {
        hasError : false,
        msg : '',
    },

    bookPrice : {
        hasError : false,
        msg : '',
    }

}

export default error;