USE [SWP391_SU24]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 7/9/2024 4:30:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[AccountID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [varchar](50) NOT NULL,
	[Password] [varchar](50) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
	[Phone] [nchar](10) NULL,
	[Email] [varchar](50) NULL,
	[Address] [nvarchar](200) NULL,
	[Status] [bit] NULL,
	[RoleID] [int] NOT NULL,
	[CampusID] [int] NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[AccountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Campus]    Script Date: 7/9/2024 4:30:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Campus](
	[CampusID] [int] IDENTITY(1,1) NOT NULL,
	[CampusName] [nvarchar](50) NOT NULL,
	[Address] [nvarchar](200) NOT NULL,
 CONSTRAINT [PK_Campus] PRIMARY KEY CLUSTERED 
(
	[CampusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 7/9/2024 4:30:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [nvarchar](50) NOT NULL,
	[Detail] [nvarchar](250) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 7/9/2024 4:30:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discount](
	[DiscountID] [int] IDENTITY(1,1) NOT NULL,
	[Value] [int] NOT NULL,
	[Code] [varchar](50) NOT NULL,
	[StartDate] [datetime] NOT NULL,
	[EndDate] [datetime] NOT NULL,
	[MaxDiscount] [money] NULL,
	[Quantity] [int] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Discount] PRIMARY KEY CLUSTERED 
(
	[DiscountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[General]    Script Date: 7/9/2024 4:30:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[General](
	[GeneralID] [int] NOT NULL,
	[Email] [varchar](50) NULL,
	[Phone] [nchar](10) NULL,
	[NameApp] [nvarchar](50) NULL,
	[Address] [nvarchar](200) NULL,
	[LogoImage] [nvarchar](200) NULL,
	[FivicoImage] [nvarchar](200) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 7/9/2024 4:30:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[AccountID] [int] NOT NULL,
	[OrderDate] [datetime] NOT NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 7/9/2024 4:30:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[OrderID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[UnitPrice] [money] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Note] [nvarchar](500) NULL,
	[DiscountID] [int] NOT NULL,
 CONSTRAINT [PK_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC,
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 7/9/2024 4:30:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ProductID] [int] IDENTITY(1,1) NOT NULL,
	[ProductName] [nvarchar](50) NOT NULL,
	[Image] [nvarchar](250) NULL,
	[Description] [nvarchar](250) NULL,
	[Recipe] [nvarchar](500) NULL,
	[Status] [bit] NULL,
	[isHot] [bit] NULL,
	[CategoryID] [int] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductSize]    Script Date: 7/9/2024 4:30:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductSize](
	[ProductID] [int] NOT NULL,
	[SizeID] [int] NOT NULL,
	[Price] [money] NOT NULL,
 CONSTRAINT [PK_ProductSize] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC,
	[SizeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 7/9/2024 4:30:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[RoleID] [int] IDENTITY(1,1) NOT NULL,
	[RoleName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Size]    Script Date: 7/9/2024 4:30:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Size](
	[SizeID] [int] IDENTITY(1,1) NOT NULL,
	[Type] [varchar](50) NOT NULL,
	[Description] [nvarchar](50) NULL,
 CONSTRAINT [PK_Size] PRIMARY KEY CLUSTERED 
(
	[SizeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Supplier]    Script Date: 7/9/2024 4:30:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Supplier](
	[SupplierID] [int] IDENTITY(1,1) NOT NULL,
	[SupplierName] [nvarchar](100) NOT NULL,
	[Contact] [nvarchar](200) NULL,
	[Address] [nvarchar](200) NULL,
 CONSTRAINT [PK_Supplier] PRIMARY KEY CLUSTERED 
(
	[SupplierID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([AccountID], [Username], [Password], [Name], [Phone], [Email], [Address], [Status], [RoleID], [CampusID]) VALUES (1, N'admin', N'1234', N'Admin', N'0283334839', N't2coffee@gmail.com', N'Hà Nội', 1, 1, 1)
INSERT [dbo].[Account] ([AccountID], [Username], [Password], [Name], [Phone], [Email], [Address], [Status], [RoleID], [CampusID]) VALUES (2, N'staff1', N'123', N'Staff1', N'0923482734', N'staff1@gmail.com', N'Hà Nội', 1, 2, 1)
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Campus] ON 

INSERT [dbo].[Campus] ([CampusID], [CampusName], [Address]) VALUES (1, N'HOLA', N'Hòa Lạc, Hà Nội')
INSERT [dbo].[Campus] ([CampusID], [CampusName], [Address]) VALUES (2, N'HCM', N'Hồ Chí Minh')
SET IDENTITY_INSERT [dbo].[Campus] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Detail]) VALUES (1, N'Coffee', N'All type coffee')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Detail]) VALUES (2, N'Smoothie', N'All type smoothie')
INSERT [dbo].[Category] ([CategoryID], [CategoryName], [Detail]) VALUES (3, N'Fruit juice', N'All type fruit juice')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Discount] ON 

INSERT [dbo].[Discount] ([DiscountID], [Value], [Code], [StartDate], [EndDate], [MaxDiscount], [Quantity], [Status]) VALUES (1, 10, N'HA113', CAST(N'2024-05-01T00:00:00.000' AS DateTime), CAST(N'2024-05-17T00:00:00.000' AS DateTime), 20000.0000, 20, 0)
INSERT [dbo].[Discount] ([DiscountID], [Value], [Code], [StartDate], [EndDate], [MaxDiscount], [Quantity], [Status]) VALUES (3, 15, N'HA112', CAST(N'2024-05-01T00:00:00.000' AS DateTime), CAST(N'2024-05-30T00:00:00.000' AS DateTime), 20000.0000, 20, 1)
INSERT [dbo].[Discount] ([DiscountID], [Value], [Code], [StartDate], [EndDate], [MaxDiscount], [Quantity], [Status]) VALUES (9, 18, N'HA112', CAST(N'2024-06-14T00:00:00.000' AS DateTime), CAST(N'2024-06-30T00:00:00.000' AS DateTime), 26000.0000, 10, 1)
SET IDENTITY_INSERT [dbo].[Discount] OFF
GO
SET IDENTITY_INSERT [dbo].[Order] ON 

INSERT [dbo].[Order] ([OrderID], [AccountID], [OrderDate]) VALUES (1, 2, CAST(N'2024-05-29T00:00:00.000' AS DateTime))
INSERT [dbo].[Order] ([OrderID], [AccountID], [OrderDate]) VALUES (2, 2, CAST(N'2024-05-29T00:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Order] OFF
GO
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Note], [DiscountID]) VALUES (1, 2, 20000.0000, 1, N'Ít đá', 1)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Note], [DiscountID]) VALUES (1, 3, 35000.0000, 1, NULL, 1)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Note], [DiscountID]) VALUES (2, 1, 25000.0000, 1, NULL, 1)
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (1, N'Milk coffee', NULL, NULL, NULL, 1, 1, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (2, N'Black coffee', NULL, NULL, NULL, 1, NULL, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (3, N'Coconut coffee', NULL, NULL, NULL, 1, NULL, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (4, N'Latte', NULL, NULL, NULL, 1, 1, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (5, N'Americano', NULL, NULL, NULL, 1, NULL, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (6, N'Espresso', NULL, NULL, NULL, 1, NULL, 1)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (7, N'Avocado Smoothie', NULL, NULL, NULL, 1, NULL, 2)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (8, N'Orange yogurt smoothie', NULL, NULL, NULL, 1, NULL, 2)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (9, N'Grape smoothie', NULL, NULL, NULL, 1, NULL, 2)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (10, N'Watermelon juice', NULL, NULL, NULL, 1, NULL, 2)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (11, N'Pineapple smoothie', NULL, NULL, NULL, 1, NULL, 2)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (12, N'Green bean and pennywort smoothie', NULL, NULL, NULL, 1, NULL, 2)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (13, N'Strawberry smoothie', NULL, NULL, NULL, 1, NULL, 2)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (14, N'Carrot smoothie', NULL, NULL, NULL, 1, NULL, 2)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (15, N'Custard smoothie', NULL, NULL, NULL, 1, NULL, 2)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (16, N'Apple lemon smoothie', NULL, NULL, NULL, 1, NULL, 2)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (17, N'Orange juice', NULL, NULL, NULL, 1, 1, 3)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (18, N'Apple juice', NULL, NULL, NULL, 1, NULL, 3)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (19, N'Watermelon juice', NULL, NULL, NULL, 1, NULL, 3)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (20, N'Pineapple juice', NULL, NULL, NULL, 1, NULL, 3)
INSERT [dbo].[Product] ([ProductID], [ProductName], [Image], [Description], [Recipe], [Status], [isHot], [CategoryID]) VALUES (21, N'Guava juice', NULL, NULL, NULL, 1, NULL, 3)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (1, 1, 20000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (1, 2, 25000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (1, 3, 29000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (2, 1, 20000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (2, 2, 24000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (2, 3, 30000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (3, 1, 25000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (3, 2, 32000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (3, 3, 40000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (4, 1, 35000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (5, 2, 39000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (6, 2, 29000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (7, 1, 25000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (7, 2, 29000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (7, 3, 35000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (8, 1, 32000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (8, 2, 40000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (8, 3, 45000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (9, 2, 38000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (10, 2, 38000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (11, 2, 29000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (12, 2, 35000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (13, 2, 37000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (14, 2, 39000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (15, 2, 30000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (16, 2, 39000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (17, 2, 35000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (18, 2, 32000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (19, 2, 30000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (20, 2, 32000.0000)
INSERT [dbo].[ProductSize] ([ProductID], [SizeID], [Price]) VALUES (21, 2, 40000.0000)
GO
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([RoleID], [RoleName]) VALUES (1, N'Admin')
INSERT [dbo].[Role] ([RoleID], [RoleName]) VALUES (2, N'Staff')
SET IDENTITY_INSERT [dbo].[Role] OFF
GO
SET IDENTITY_INSERT [dbo].[Size] ON 

INSERT [dbo].[Size] ([SizeID], [Type], [Description]) VALUES (1, N'M', NULL)
INSERT [dbo].[Size] ([SizeID], [Type], [Description]) VALUES (2, N'L', NULL)
INSERT [dbo].[Size] ([SizeID], [Type], [Description]) VALUES (3, N'XL', NULL)
SET IDENTITY_INSERT [dbo].[Size] OFF
GO
SET IDENTITY_INSERT [dbo].[Supplier] ON 

INSERT [dbo].[Supplier] ([SupplierID], [SupplierName], [Contact], [Address]) VALUES (1, N'Tập Đoàn Intimex', N'+84 28 3820 8052', N'61 Nguyễn Văn Giai, Phường Dakao, Quận 1,Tp.Hồ Chí Minh, Việt Nam')
INSERT [dbo].[Supplier] ([SupplierID], [SupplierName], [Contact], [Address]) VALUES (2, N'Công Ty CP Cà Phê Trung Nguyên', N'1900 6011', N'82-84 Bùi Thị Xuân, P. Bến Thành, Q.1, Tp Hồ Chí Minh')
INSERT [dbo].[Supplier] ([SupplierID], [SupplierName], [Contact], [Address]) VALUES (4, N'Coffee Duy Nam', N'0979459322', N'Ha Noi')
INSERT [dbo].[Supplier] ([SupplierID], [SupplierName], [Contact], [Address]) VALUES (8, N'kkkkk', N'0979459321', N'Chi Linh-Hai Duong')
SET IDENTITY_INSERT [dbo].[Supplier] OFF
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Campus] FOREIGN KEY([CampusID])
REFERENCES [dbo].[Campus] ([CampusID])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Campus]
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Role] FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([RoleID])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Role]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Account] FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([AccountID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Account]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Discount] FOREIGN KEY([DiscountID])
REFERENCES [dbo].[Discount] ([DiscountID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Discount]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Order] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Order]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Product]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Category] ([CategoryID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
ALTER TABLE [dbo].[ProductSize]  WITH CHECK ADD  CONSTRAINT [FK_ProductSize_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID])
GO
ALTER TABLE [dbo].[ProductSize] CHECK CONSTRAINT [FK_ProductSize_Product]
GO
ALTER TABLE [dbo].[ProductSize]  WITH CHECK ADD  CONSTRAINT [FK_ProductSize_Size] FOREIGN KEY([SizeID])
REFERENCES [dbo].[Size] ([SizeID])
GO
ALTER TABLE [dbo].[ProductSize] CHECK CONSTRAINT [FK_ProductSize_Size]
GO
