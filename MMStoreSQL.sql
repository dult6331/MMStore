USE [master]
GO
/****** Object:  Database [MMStore]    Script Date: 7/20/2021 12:13:10 PM ******/
CREATE DATABASE [MMStore]
GO
ALTER DATABASE [MMStore] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [MMStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [MMStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [MMStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [MMStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [MMStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [MMStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [MMStore] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [MMStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [MMStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [MMStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [MMStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [MMStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [MMStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [MMStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [MMStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [MMStore] SET  ENABLE_BROKER 
GO
ALTER DATABASE [MMStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [MMStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [MMStore] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [MMStore] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [MMStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [MMStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [MMStore] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [MMStore] SET RECOVERY FULL 
GO
ALTER DATABASE [MMStore] SET  MULTI_USER 
GO
ALTER DATABASE [MMStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [MMStore] SET DB_CHAINING OFF 
GO
ALTER DATABASE [MMStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [MMStore] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [MMStore] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'MMStore', N'ON'
GO
ALTER DATABASE [MMStore] SET QUERY_STORE = OFF
GO
USE [MMStore]
GO
/****** Object:  Table [dbo].[Accounts]    Script Date: 7/20/2021 12:13:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Accounts](
	[AccountID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [varchar](50) NOT NULL,
	[Password] [varchar](50) NOT NULL,
	[IsAdministrator] [bit] NOT NULL,
 CONSTRAINT [PK_Accounts] PRIMARY KEY CLUSTERED 
(
	[AccountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 7/20/2021 12:13:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [nvarchar](50) NOT NULL,
	[CategoryImage] [varchar](max) NULL,
 CONSTRAINT [PK_Categories] PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Manufacturers]    Script Date: 7/20/2021 12:13:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Manufacturers](
	[ManufacturerID] [int] IDENTITY(1,1) NOT NULL,
	[ManufactureName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Manufacturers] PRIMARY KEY CLUSTERED 
(
	[ManufacturerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 7/20/2021 12:13:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[OrderID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
 CONSTRAINT [PK_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC,
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 7/20/2021 12:13:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[AccountID] [int] NOT NULL,
	[OrderDate] [date] NOT NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductImages]    Script Date: 7/20/2021 12:13:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductImages](
	[ImageID] [int] IDENTITY(1,1) NOT NULL,
	[ProductID] [int] NOT NULL,
	[ImageUrl] [varchar](max) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ImageID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 7/20/2021 12:13:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[ProductID] [int] IDENTITY(1,1) NOT NULL,
	[ProductName] [nvarchar](max) NOT NULL,
	[UnitsPrice] [money] NOT NULL,
	[CategoryID] [int] NOT NULL,
	[UnitsInStock] [int] NOT NULL,
	[Description] [nvarchar](max) NOT NULL,
	[ManufacturerID] [int] NOT NULL,
	[IsContinue] [bit] NOT NULL,
	[Stars] [int] NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Accounts] ON 

INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (1, N'Admin1', N'1231232@aA', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (2, N'Admin2', N'123123@aA', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (3, N'User1', N'123123@aA', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (4, N'User2', N'123123@aA', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (5, N'User3', N'12@aA3123', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (6, N'User4', N'12@aA3123', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (7, N'User5', N'12312@aA3', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (27, N'aliquet.libero@purusgravida.org', N'003@aA74728', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (28, N'tempus.eu@vel.edu', N'98236@aA590', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (29, N'est.Nunc@eratneque.net', N'38769@aA110', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (30, N'aliquam.arcu.Aliquam@nonummy.edu', N'0745@aA5890', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (31, N'convallis.est.vitae@consectetuercursuset.co.uk', N'52@aA504791', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (32, N'Aenean.eget.magna@ultricesa.ca', N'9670463@aA2', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (33, N'sed@torquentperconubia.ca', N'4@aA0826759', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (34, N'risus.at@velsapien.com', N'575@aA03013', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (35, N'Lorem@Phasellus.co.uk', N'469@aA00214', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (36, N'Proin@feugiat.ca', N'11@aA545373', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (37, N'Quisque@magnaaneque.net', N'792373@aA84', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (38, N'malesuada.vel@ante.com', N'46563@aA064', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (39, N'magna.Nam.ligula@urnaet.co.uk', N'61@aA048577', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (40, N'tempus.eu.ligula@dui.edu', N'5818947@aA9', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (41, N'Lorem.ipsum.dolor@dolorNulla.com', N'88@aA256323', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (42, N'mi@molestiepharetranibh.ca', N'305@aA69052', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (43, N'et@estvitaesodales.co.uk', N'9138@aA7041', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (44, N'ante.blandit.viverra@ProindolorNulla.org', N'6384@aA7258', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (45, N'fermentum.metus@odio.org', N'18491@aA464', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (46, N'amet.risus.Donec@nulla.org', N'6985269@aA7', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (47, N'egestas@Curabitur.ca', N'@aA23423', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (48, N'orci.luctus@egestasadui.org', N'809946@aA60', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (49, N'cursus@Nullam.org', N'@aA234234', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (50, N'semper.Nam@vitaemauris.com', N'7198@aA1108', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (51, N'arcu@vitaerisus.ca', N'09622@aA962', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (52, N'Mauris.vel@eratsemperrutrum.co.uk', N'02339@aA606', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (53, N'Aliquam@penatibus.com', N'982@aA62322', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (54, N'laoreet.libero@turpisNulla.edu', N'080@aA50472', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (55, N'et.magnis@eratEtiamvestibulum.co.uk', N'772137@aA13', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (56, N'quam.dignissim.pharetra@hendreritconsectetuer.org', N'@aA234234', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (57, N'id@fermentummetusAenean.com', N'@aA234234', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (58, N'amet@telluseu.co.uk', N'02339@aA606', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (59, N'nibh.lacinia@pretiumnequeMorbi.com', N'82009992', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (60, N'blandit.enim@elitCurabitursed.edu', N'@aA234234', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (61, N'molestie.dapibus.ligula@elitAliquam.ca', N'772137@aA13', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (62, N'Vestibulum.ut@sem.org', N'62155A@a108', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (63, N'nec.tempus.mauris@risusQuisque.org', N'5017A@a7346', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (64, N'ornare.Fusce@luctussit.edu', N'2333445A@a2', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (65, N'molestie.dapibus@Donec.net', N'403A@a41303', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (66, N'aliquam.enim.nec@ametornare.com', N'772137@aA13', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (67, N'id.risus@condimentumeget.org', N'5205565A@a3', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (68, N'ante@ornarelibero.co.uk', N'692A@a68727', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (69, N'Mauris.molestie@risusaultricies.com', N'2481197A@a9', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (70, N'a@egetmassaSuspendisse.edu', N'772137@aA13', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (71, N'adipiscing.ligula.Aenean@dolor.co.uk', N'805A@a20580', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (72, N'adipiscing.enim.mi@lacusvestibulum.net', N'4644100A@a6', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (73, N'luctus.ipsum@anteipsum.edu', N'772137@aA13', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (74, N'elit.pretium@morbi.com', N'316A@a10829', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (75, N'lacus.vestibulum@nonummyultricies.ca', N'7041754A@a7', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (76, N'elit.erat@arcuacorci.net', N'8066A@a5086', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (77, N'sollicitudin.commodo.ipsum@lacus.com', N'130723A@a40', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (78, N'pellentesque.eget.dictum@justositamet.ca', N'475A@a38787', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (79, N'libero.et@pedeetrisus.net', N'9911A@a5143', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (80, N'fermentum.metus@Aeneaneuismod.net', N'48784A@a001', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (81, N'neque.Sed.eget@nequeMorbiquis.edu', N'095A@a52042', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (82, N'vitae.aliquam@Pellentesqueut.co.uk', N'9096A@a8311', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (83, N'parturient.montes@lobortismaurisSuspendisse.edu', N'7295A@a4669', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (84, N'velit@amet.edu', N'12815A@a968', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (85, N'Curabitur.egestas@luctus.co.uk', N'72A@a988213', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (86, N'libero.et.tristique@risusIn.org', N'7998A@a3166', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (87, N'Praesent.interdum@pharetrased.co.uk', N'314097A@a90', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (88, N'sodales.purus.in@Suspendisse.edu', N'764A@a24399', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (89, N'ac@adipiscingelitEtiam.net', N'043682A@a03', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (90, N'dui@diam.co.uk', N'76A@a775215', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (91, N'convallis@bibendum.edu', N'349333A@a20', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (92, N'lobortis.quis@erat.ca', N'8128A@a9451', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (93, N'ornare@nibhAliquam.org', N'797A@a85665', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (94, N'neque@eu.org', N'2584183A@a9', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (95, N'ac@laoreet.co.uk', N'414235A@a02', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (96, N'auctor@urnaUt.co.uk', N'58A@a915410', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (97, N'morbi.tristique.senectus@nunc.net', N'49A@a197068', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (98, N'sapien@Maurisvestibulum.co.uk', N'767A@a84781', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (99, N'arcu.Aliquam.ultrices@ac.co.uk', N'248033A@a17', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (100, N'aliquet.Proin.velit@bibendumullamcorperDuis.org', N'0008066A@a9', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (101, N'nibh.vulputate@utpharetrased.co.uk', N'749A@a11320', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (102, N'mollis@eu.co.uk', N'24797A@a116', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (103, N'egestas.a@vulputateullamcorpermagna.com', N'33A@a603280', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (104, N'fermentum.vel.mauris@loremluctus.edu', N'8999A@a4187', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (105, N'sit.amet.consectetuer@suscipitnonummy.net', N'000610A@a26', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (106, N'lorem@In.org', N'42A@a778233', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (107, N'malesuada.fringilla@VivamusrhoncusDonec.net', N'81486A@a420', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (108, N'lorem.luctus@ategestasa.edu', N'2260A@a0485', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (109, N'mauris.sagittis@acarcu.edu', N'86821A@a184', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (110, N'Morbi.quis.urna@feugiat.net', N'7A@a4089504', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (111, N'nunc@iaculisquis.net', N'743384A@a95', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (112, N'leo.Vivamus@Vivamussitamet.co.uk', N'3394691A@a2', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (113, N'sit@Phaselluselitpede.edu', N'7A@a1731907', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (114, N'et.euismod@consectetuer.org', N'405757A@a78', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (115, N'Donec.est@Phasellusfermentum.edu', N'9A@a3498659', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (116, N'tincidunt@tellusid.edu', N'38408A@a374', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (117, N'neque.venenatis@euodioPhasellus.co.uk', N'0794A@a7908', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (118, N'Proin@elitsed.co.uk', N'78146A@a578', 1)
GO
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (119, N'placerat@necante.ca', N'131A@a20326', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (120, N'arcu@euismodmauriseu.org', N'7775A@a6347', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (121, N'eu.accumsan.sed@mollis.org', N'32543A@a080', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (122, N'tellus.imperdiet.non@gravidanon.ca', N'30674A@a040', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (123, N'eget.dictum@utmolestiein.ca', N'3459A@a2965', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (124, N'sit.amet@egestasAliquam.co.uk', N'24234sdsad#$S@$ds', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (125, N'ligula.tortor@ipsumcursus.com', N'22411ad#$@$dS154', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (126, N'ornare.lectus@non.edu', N'99540221', 1)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (127, N'user112', N'123@123Aa', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (128, N'user113', N'123@123Aa', 0)
INSERT [dbo].[Accounts] ([AccountID], [Username], [Password], [IsAdministrator]) VALUES (129, N'user114', N'123@123Aa', 0)
SET IDENTITY_INSERT [dbo].[Accounts] OFF
GO
SET IDENTITY_INSERT [dbo].[Categories] ON 

INSERT [dbo].[Categories] ([CategoryID], [CategoryName], [CategoryImage]) VALUES (1, N'Mouse', N'./public/images/data/anh.png')
INSERT [dbo].[Categories] ([CategoryID], [CategoryName], [CategoryImage]) VALUES (2, N'Keyboard', N'./public/images/data/anh.png')
SET IDENTITY_INSERT [dbo].[Categories] OFF
GO
SET IDENTITY_INSERT [dbo].[Manufacturers] ON 

INSERT [dbo].[Manufacturers] ([ManufacturerID], [ManufactureName]) VALUES (1, N'Logitech')
INSERT [dbo].[Manufacturers] ([ManufacturerID], [ManufactureName]) VALUES (2, N'Corsair')
INSERT [dbo].[Manufacturers] ([ManufacturerID], [ManufactureName]) VALUES (3, N'Razer')
INSERT [dbo].[Manufacturers] ([ManufacturerID], [ManufactureName]) VALUES (4, N'Steelseries')
INSERT [dbo].[Manufacturers] ([ManufacturerID], [ManufactureName]) VALUES (5, N'E-Dra')
INSERT [dbo].[Manufacturers] ([ManufacturerID], [ManufactureName]) VALUES (6, N'Asus')
INSERT [dbo].[Manufacturers] ([ManufacturerID], [ManufactureName]) VALUES (7, N'Other')
SET IDENTITY_INSERT [dbo].[Manufacturers] OFF
GO
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (1, 4, 2)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (1, 5, 1)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (2, 14, 2)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (2, 17, 2)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (3, 15, 2)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (3, 24, 3)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (4, 15, 2)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (4, 16, 1)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (5, 17, 2)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (5, 18, 2)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (6, 19, 1)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (6, 20, 2)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (7, 30, 1)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (7, 33, 2)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (7, 35, 1)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (9, 18, 1)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (9, 33, 2)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [Quantity]) VALUES (9, 38, 1)
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([OrderID], [AccountID], [OrderDate]) VALUES (1, 1, CAST(N'2021-01-01' AS Date))
INSERT [dbo].[Orders] ([OrderID], [AccountID], [OrderDate]) VALUES (2, 2, CAST(N'2021-02-01' AS Date))
INSERT [dbo].[Orders] ([OrderID], [AccountID], [OrderDate]) VALUES (3, 3, CAST(N'2021-03-01' AS Date))
INSERT [dbo].[Orders] ([OrderID], [AccountID], [OrderDate]) VALUES (4, 4, CAST(N'2021-04-01' AS Date))
INSERT [dbo].[Orders] ([OrderID], [AccountID], [OrderDate]) VALUES (5, 5, CAST(N'2021-05-01' AS Date))
INSERT [dbo].[Orders] ([OrderID], [AccountID], [OrderDate]) VALUES (6, 6, CAST(N'2021-01-01' AS Date))
INSERT [dbo].[Orders] ([OrderID], [AccountID], [OrderDate]) VALUES (7, 1, CAST(N'2021-07-16' AS Date))
INSERT [dbo].[Orders] ([OrderID], [AccountID], [OrderDate]) VALUES (9, 3, CAST(N'2021-07-18' AS Date))
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductImages] ON 

INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (15, 4, N'./public/images/mouse/logitech1/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (16, 4, N'./public/images/mouse/logitech1/chuot2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (17, 4, N'./public/images/mouse/logitech1/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (18, 5, N'./public/images/mouse/logitech2/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (19, 5, N'./public/images/mouse/logitech2/chuot2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (20, 5, N'./public/images/mouse/logitech12/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (21, 6, N'./public/images/mouse/corsair1/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (22, 6, N'./public/images/mouse/corsair1/chuot2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (23, 6, N'./public/images/mouse/corsair1/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (25, 14, N'./public/images/mouse/corsair2/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (26, 14, N'./public/images/mouse/corsair2/chuot2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (27, 14, N'./public/images/mouse/corsair2/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (28, 15, N'./public/images/mouse/corsair3/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (29, 15, N'./public/images/mouse/corsair3/chuot2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (30, 15, N'./public/images/mouse/corsair3/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (31, 16, N'./public/images/mouse/corsair4/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (32, 16, N'./public/images/mouse/corsair4/chuot2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (33, 16, N'./public/images/mouse/corsair4/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (34, 17, N'./public/images/mouse/razer1/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (35, 17, N'./public/images/mouse/razer1/chuot2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (36, 17, N'./public/images/mouse/razer1/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (37, 18, N'./public/images/mouse/razer2/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (38, 18, N'./public/images/mouse/razer2/chuot4.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (39, 18, N'./public/images/mouse/razer3/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (40, 19, N'./public/images/mouse/razer4/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (41, 19, N'./public/images/mouse/razer4/chuot2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (42, 19, N'./public/images/mouse/razer4/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (43, 20, N'./public/images/mouse/razer5/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (44, 20, N'./public/images/mouse/razer5/chuot2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (45, 20, N'./public/images/mouse/razer5/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (46, 21, N'./public/images/mouse/ss1/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (47, 21, N'./public/images/mouse/ss1/chuot2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (48, 21, N'./public/images/mouse/ss1/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (49, 22, N'./public/images/mouse/ss2/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (50, 22, N'./public/images/mouse/ss2/chuot2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (51, 22, N'./public/images/mouse/ss2/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (52, 24, N'./public/images/mouse/ss3/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (53, 24, N'./public/images/mouse/ss3/chuot2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (54, 24, N'./public/images/mouse/ss3/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (55, 25, N'./public/images/mouse/ss4/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (56, 25, N'./public/images/mouse/ss4/chuot2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (57, 25, N'./public/images/mouse/ss4/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (58, 26, N'./public/images/mouse/edra1/chuot1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (59, 26, N'./public/images/mouse/edra1/chuot2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (60, 26, N'./public/images/mouse/4/chuot3.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (64, 27, N'./public/images/keyboard/16/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (65, 27, N'./public/images/keyboard/16/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (66, 28, N'./public/images/keyboard/17/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (67, 28, N'./public/images/keyboard/18/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (68, 29, N'./public/images/keyboard/18/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (69, 29, N'./public/images/keyboard/19/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (70, 30, N'./public/images/keyboard/19/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (71, 30, N'./public/images/keyboard/20/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (72, 31, N'./public/images/keyboard/20/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (73, 31, N'./public/images/keyboard/21/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (74, 32, N'./public/images/keyboard/21/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (75, 32, N'./public/images/keyboard/22/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (76, 33, N'./public/images/keyboard/22/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (77, 33, N'./public/images/keyboard/23/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (78, 34, N'./public/images/keyboard/23/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (79, 34, N'./public/images/keyboard/24/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (80, 35, N'./public/images/keyboard/24/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (81, 35, N'./public/images/keyboard/25/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (82, 36, N'./public/images/keyboard/25/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (83, 36, N'./public/images/keyboard/26/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (84, 37, N'./public/images/keyboard/26/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (85, 37, N'./public/images/keyboard/27/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (86, 38, N'./public/images/keyboard/27/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (87, 38, N'./public/images/keyboard/28/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (88, 39, N'./public/images/keyboard/28/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (89, 39, N'./public/images/keyboard/29/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (90, 41, N'./public/images/keyboard/29/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (91, 41, N'./public/images/keyboard/30/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (92, 42, N'./public/images/keyboard/30/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (93, 42, N'./public/images/keyboard/16/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (94, 43, N'./public/images/keyboard/16/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (95, 43, N'./public/images/keyboard/17/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (96, 44, N'./public/images/keyboard/17/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (97, 44, N'./public/images/keyboard/18/bp2.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (98, 45, N'./public/images/keyboard/18/bp1.jpg')
INSERT [dbo].[ProductImages] ([ImageID], [ProductID], [ImageUrl]) VALUES (99, 45, N'./public/images/keyboard/19/bp2.jpg')
SET IDENTITY_INSERT [dbo].[ProductImages] OFF
GO
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (4, N'Chuột Chơi game Không dây Logitech G502 Lightspeed (USB/RGB/Đen) 
', 3199000.0000, 1, 20, N'Công nghệ Lightspeed không dây,Mắt cảm biến Hero 16K
', 1, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (5, N'Chuột chơi game Logitech G PRO HERO Gaming Black
', 1299000.0000, 1, 20, N'CẢM BIẾN HERO 16K
', 3, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (6, N'Chuột không dây Corsair Katar Pro (PMW3325) (CH-931C011-AP)', 889000.0000, 1, 20, N'Công nghệ SlipStream Wireless,Trọng lượng nhẹ nhàng', 1, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (14, N'Chuột Corsair Nightsword RGB (CH-9306011-AP)
', 1949000.0000, 1, 15, N'Tuỳ chỉnh trọng lượng dễ dàng,Thiết kế thoải máiThiết kế thoải mái
', 2, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (15, N'Chuột chơi game Corsair Harpoon RGB PRO (CH-9301111AP)
', 499000.0000, 1, 10, N'"Độ chính xác cực cao,Chuột cực kỳ bền,Nút bấm tùy chỉnh

"
', 2, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (16, N'Chuột chơi game Corsair Dark Core Wireless RGB SE
', 2489000.0000, 1, 10, N'Công nghệ không dây chất lượng,Tùy chỉnh tốc độ cảm biến

', 2, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (17, N'Chuột chơi game Razer DeathAdder V2 RGB Gaming Mouse Black (RZ01-03210100-R3M1)
', 1689000.0000, 1, 10, N'FORM ERGONOMIC HUYỀN THOẠI ,SWITCH QUANG HỌC RAZER

', 3, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (18, N'Chuột chơi game Razer Basilisk Essential Gaming (RZ01-02650100-R3M1)', 1139000.0000, 1, 100, N'Mắt cảm biến Pixart S3389 cao cấp nhất của Razer', 1, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (19, N'Chuột chơi game Razer DeathAdder Essential Right-Handed (RZ01-02540100-R3M1)', 1349000.0000, 1, 50, N'Phiên bản Razer Deathadder giá rẻ cho các game thủ yêu thích sự đơn giản nhưng chất lượng', 1, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (20, N'Chuột chơi game Razer Basilisk V2 RGB Gaming Mouse Black (RZ01-03160100-R3M1)
', 2199000.0000, 1, 17, N'11 NÚT BẤM CÓ THỂ LẬP TRÌNH,TÙY CHỈNH CẤU HÌNH THEO Ý MUỐN

', 3, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (21, N'Chuột chơi game SteelSeries Rival 650 Wireless (62456)
', 2369000.0000, 1, 13, N'Công nghệ sạc nhanh tích hợp,Quantum Wireless

', 4, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (22, N'Chuột chơi game SteelSeries Sensei 310 Black (RGB) (62432)
', 1289000.0000, 1, 9, N'Chất liệu bền bỉ,Thiết kế đối xứng

', 4, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (24, N'Chuột chơi game SteelSeries Rival 310 RGB black (62433)
', 1289000.0000, 1, 5, N'Mắt đọc chuẩn thi đấu Esport tiên tiến nhất trên Thế Giới
', 4, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (25, N'Chuột chơi game SteelSeries Rival 710 (62334)
', 2149000.0000, 1, 10, N'Tính năng rung đặc biệt,Màn hình OLED tùy biến

', 4, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (26, N'Chuột không dây E-Dra EM620W (USB /RGB/ Đen)
', 689000.0000, 1, 15, N'Cảm biến Pixart 3325 chât lượng cao
', 5, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (27, N'Bàn phím cơ Asus ROG Strix Flare Pink (XA01)
', 3799000.0000, 2, 10, N'Các switch cơ Cherry MX RGB,Các phím đa phương tiện tiện dụng

', 6, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (28, N'Bàn phím cơ Corsair K63 Cherry MX Red (USB/Red Led) (CH-9115020-NA)
', 1989000.0000, 2, 17, N'Thiết kế Tenkeyless loại bỏ khu vực phím số,Hệ thống Led màu đỏ, có thể tùy chỉnh hiệu ứng qua Driver của Corsair


', 2, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (29, N'Bàn phím cơ Leopold FC900R PD PBT Doubleshot Brown switch Black
', 3249000.0000, 2, 13, N'Switch sử dụng 100% Cherry Chính Hãng Germany + Keycap PBT Doubleshot
', 7, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (30, N'Bàn phím cơ Ducky One Horizon Fullsize Red Switch
', 2299000.0000, 2, 19, N'Switch sử dụng 100% Cherry Chính Hãng Germany + Keycap PBT Doubleshot
', 7, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (31, N'Bàn phím SteelSeries APEX 3 RGB Water Resistant Whisper-Quiet Switches Gaming Black (64795)', 1643000.0000, 2, 9, N'Tiêu chuẩn trống nước IP32
', 1, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (32, N'Bàn phím cơ SteelSeries APEX 5 RGB Hybrid switch Mechanical Gaming Black (64532)
', 2889000.0000, 2, 20, N'Màn hình thông minh OLED ,Kê tay nam chân từ tính cao cấp

', 4, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (33, N'Bàn phím cơ AKKO 3108S RGB PRO Black Cherry MX Red Switch
', 2399000.0000, 2, 16, N'"Switch Cherry – Switch cao cấp đến từ Đức,Keycap PBT Double Shot xuyên Led siêu bền 
"
', 7, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (34, N'Bàn phím cơ E-Dra EK3104 Pro (Cherry Blue switch/PBT/USB/Đen)
', 1689000.0000, 2, 19, N'Gia công tốt ,thiết kế chắc chắn,Thiết kế cổ điển

', 5, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (35, N'Bàn phím AKKO 3087 Dragon Ball Z Goku (Cherry Switch Brown)
', 2299000.0000, 2, 13, N'Thiết kế bắt mắt,Sử dụng Switch Cherry MX cao cấp

', 7, 0, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (36, N'Bàn phím cơ Leopold FC750RPD Blue Star (USB/PBT Doubleshot/White Navy/Red case/Red sw)
', 3149000.0000, 2, 17, N'Sử dụng bộ Switch Cherry 100% chính hãng từ nước Đức,Keycap đặc trưng mang thương hiệu Leopold

', 7, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (37, N'Bàn phím cơ Edra EK384 (USB/RGB/Huano Blue sw)
', 799000.0000, 2, 6, N'Keycap PBT Dye-sub bền bỉ và cho cảm giác gõ tốt
', 5, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (38, N'Bàn phím cơ Corsair K95 Platinum XT RGB Mechanical Gaming Cherry MX Blue Switch (CH-9127411-NA)
', 4299000.0000, 2, 18, N'Phù hợp với mọi phong cách,Độ bền tối đa

', 2, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (39, N'Bàn phím cơ Varmilo VA87M Koi - Mechanical Cherry Brown switch
', 3299000.0000, 2, 23, N'Thiết kế đặc biệt,Font chữ độc đáo

', 7, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (41, N'Bàn phím cơ Logitech G813 Lightsync RGB Mechanical Romer G Clicky Gaming Keyboard Black
', 2999000.0000, 2, 17, N'Mỏng đến không tưởng,Bàn phím cơ tiên tiến

', 1, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (42, N'Bàn phím cơ Leopold FC750R PD Blue switch (White Mint)
', 3269000.0000, 2, 14, N'Keycap đặc trưng mang thương hiệu Leopold,Miếng vải ngăn cách tiếng ồn

', 7, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (43, N'Bàn phím cơ Filco Majestouch Minila Air Matcha blue switch (FFBT67MC/ECW)
', 3199000.0000, 2, 18, N'100% Cherry Switch được tinh chỉnh lực nhấn và độ mượt
', 7, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (44, N'Bàn phím cơ Filco Camouflage R Blue switch 87/ FKBN87MC/EMR2
', 3619000.0000, 2, 19, N'Thương hiệu Nhật Bản, thiết kế đơn điệu nhưng chất lượng Tuyệt Vời
', 7, 1, 4)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) VALUES (45, N'Bàn phím cơ RealForce R2 APC - Mechanical Topre 55gRAM switch TKL Black R2TLA-US5-BK
', 5699000.0000, 2, 4, N'Switch Topre,Điều chỉnh độ nhạy (APC)

', 1, 1, 4)
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Orders] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Orders] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Orders]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Products] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Products] ([ProductID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Products]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Accounts] FOREIGN KEY([AccountID])
REFERENCES [dbo].[Accounts] ([AccountID])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Accounts]
GO
ALTER TABLE [dbo].[ProductImages]  WITH CHECK ADD  CONSTRAINT [FK_ProductImages_Products] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Products] ([ProductID])
GO
ALTER TABLE [dbo].[ProductImages] CHECK CONSTRAINT [FK_ProductImages_Products]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Categories] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Categories] ([CategoryID])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Categories]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Manufacturers] FOREIGN KEY([ManufacturerID])
REFERENCES [dbo].[Manufacturers] ([ManufacturerID])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Manufacturers]
GO
USE [master]
GO
ALTER DATABASE [MMStore] SET  READ_WRITE 
GO
