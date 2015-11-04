module Handler.Test where

import Import

getTestR :: Handler Html
getTestR = 
	defaultLayout $
	$(widgetFile "test/test")